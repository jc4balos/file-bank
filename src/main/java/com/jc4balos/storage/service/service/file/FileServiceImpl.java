package com.jc4balos.storage.service.service.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jc4balos.storage.service.dto.FileDto;
import com.jc4balos.storage.service.dto.FileDtoView;
import com.jc4balos.storage.service.exception.SessionNotFoundException;
import com.jc4balos.storage.service.mapper.FileMapper;
import com.jc4balos.storage.service.model.AccessLevel;
import com.jc4balos.storage.service.model.FileData;
import com.jc4balos.storage.service.repository.AccessLevelRepository;
import com.jc4balos.storage.service.repository.FileRepository;
import com.jc4balos.storage.service.service.file_access.FileAccessService;
import com.jc4balos.storage.service.service.logging.LoggingService;
import com.jc4balos.storage.service.util.FileEncryptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final String folderPath = "D:\\Documents\\Repositories\\FileBank\\Storage\\";

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final Validator validator;
    private final AccessLevelRepository accessLevelRepository;

    @Autowired
    private FileAccessService fileAccessService;

    @Autowired
    private LoggingService loggingService;

    @Async
    public CompletableFuture<FileDtoView> createFile(HttpServletRequest request, FileDto fileDto)
            throws FileNotFoundException, IOException {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");

            if (userId != null) {
                Set<ConstraintViolation<FileDto>> violations = validator.validate(fileDto);
                if (!violations.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (ConstraintViolation<FileDto> violation : violations) {
                        sb.append(violation.getMessage()).append("\n");
                    }
                    throw new IllegalArgumentException("Error in creating file: " + sb.toString());
                }

                FileData file = fileMapper.toFile(fileDto, userId);
                FileEncryptor fileEncryptor = new FileEncryptor();
                MultipartFile multipartFile = fileDto.getMultipartFile();
                String originalFileName = multipartFile.getOriginalFilename();
                String encryptedFileName = fileEncryptor.encryptFileName(originalFileName);
                String extension = null;

                int i = originalFileName.lastIndexOf('.');
                if (i > 0) {
                    extension = originalFileName.substring(i + 1);
                }

                String encryptedFileNameWithExtension = encryptedFileName + "." + extension;

                String fullPath = Paths
                        .get(folderPath, encryptedFileNameWithExtension)
                        .toString();

                File destinationFile = new File(fullPath);

                file.setFilePath(fullPath);
                file.setFileType(extension);
                file.setMimeType(multipartFile.getContentType());
                file.setActive(true);

                try {
                    multipartFile.transferTo(destinationFile);
                } catch (FileNotFoundException e) {
                    throw new FileNotFoundException("File not found");
                } catch (IOException e) {
                    throw new IOException("Failed to save file");
                }

                fileRepository.save(file);
                loggingService.createLog((Long) session.getAttribute("userId"),
                        "created file " + file.getFileName());

                FileDtoView fileDtoView = fileMapper.toDtoView(file);

                Long fileId = file.getFileId();

                List<Long> accessLevelIds = accessLevelRepository.findAll().stream().map(AccessLevel::getAccessLevelId)
                        .collect(Collectors.toList());

                for (Long accessLevelIdItem : accessLevelIds) {
                    fileAccessService.addFileAccess(fileId, accessLevelIdItem);
                }

                return CompletableFuture.completedFuture(fileDtoView);

            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> deleteFile(Long fileId, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                FileData file = fileRepository.findById(fileId)
                        .orElseThrow(() -> new IllegalArgumentException("File not found"));

                file.setActive(false);
                fileRepository.save(file);

                Map<String, String> response = new HashMap<>();
                response.put("message", "File " + file.getFileName() + " moved to trash");

                loggingService.createLog((Long) session.getAttribute("userId"),
                        "deleted file " + file.getFileName());
                return response;

            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public FileDtoView restoreFile(Long fileId, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                FileData file = fileRepository.findById(fileId)
                        .orElseThrow(() -> new IllegalArgumentException("File not found"));

                file.setActive(true);
                fileRepository.save(file);

                loggingService.createLog((Long) session.getAttribute("userId"),
                        "restored file " + file.getFileName());

                return fileMapper.toDtoView(file);

            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public InputStreamResource downloadFile(Long fileId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalArgumentException("Session not found. Please log in.");
        }

        FileData file = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File not found"));

        String fullPath = file.getFilePath();
        File fileToDownload = new File(fullPath);

        try {
            FileInputStream fileInputStream = new FileInputStream(fileToDownload);
            loggingService.createLog((Long) session.getAttribute("userId"),
                    "downloaded file " + file.getFileName());
            return new InputStreamResource(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found", e);
        }
    }

}
