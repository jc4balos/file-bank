package com.example.storage.service.service.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.storage.service.dto.FileDto;
import com.example.storage.service.dto.FileDtoView;
import com.example.storage.service.exception.SessionNotFoundException;
import com.example.storage.service.mapper.FileMapper;
import com.example.storage.service.model.AccessLevel;
import com.example.storage.service.model.FileData;
import com.example.storage.service.repository.AccessLevelRepository;
import com.example.storage.service.repository.FileRepository;
import com.example.storage.service.service.file_access.FileAccessService;
import com.example.storage.service.util.FileEncryptor;

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

                // if (multipartFile.isEmpty()) {
                // throw new FileNotFoundException("No File found");
                // }

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
                file.setActive(true);

                try {
                    multipartFile.transferTo(destinationFile);
                } catch (FileNotFoundException e) {
                    throw e;
                } catch (IOException e) {
                    throw e;
                }

                fileRepository.save(file);

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
    public Map<String, String> deleteFile(Long fileId, Long userId) {
        FileData file = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File not found"));

        file.setActive(false);
        fileRepository.save(file);

        Map<String, String> response = new HashMap<>();
        response.put("message", "File " + file.getFileName() + " moved to trash");
        return response;
    }

    @Override
    public FileDtoView restoreFile(Long fileId, Long userId) {
        FileData file = fileRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("File not found"));

        file.setActive(true);
        fileRepository.save(file);

        return fileMapper.toDtoView(file);
    }

}