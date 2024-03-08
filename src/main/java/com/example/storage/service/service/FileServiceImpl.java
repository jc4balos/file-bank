package com.example.storage.service.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.storage.service.dto.FileDto;
import com.example.storage.service.dto.FileDtoView;
import com.example.storage.service.mapper.FileMapper;
import com.example.storage.service.model.FileData;
import com.example.storage.service.repository.FileRepository;
import com.example.storage.service.util.FileEncryptor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final String folderPath = "D:\\Documents\\Repositories\\FileBank\\Storage\\";

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final Validator validator;

    @Async
    public CompletableFuture<FileDtoView> createFile(FileDto fileDto) {
        try {
            Set<ConstraintViolation<FileDto>> violations = validator.validate(fileDto);
            if (!violations.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (ConstraintViolation<FileDto> violation : violations) {
                    sb.append(violation.getMessage()).append("\n");
                }
                throw new IllegalArgumentException("Error in creating file: " + sb.toString());
            }

            FileData file = fileMapper.toFile(fileDto);
            FileEncryptor fileEncryptor = new FileEncryptor();
            MultipartFile multipartFile = fileDto.getMultipartFile();
            String originalFileName = multipartFile.getOriginalFilename();
            String encryptedFileName = fileEncryptor.encryptFileName(originalFileName);
            String extension = null;

            if (!fileDto.getMultipartFile().isEmpty()) {
                int i = originalFileName.lastIndexOf('.');
                if (i > 0) {
                    extension = originalFileName.substring(i + 1);
                }

                String encryptedFileNameWithExtension = encryptedFileName + "." + extension;

                String fullPath = Paths
                        .get(folderPath, encryptedFileNameWithExtension)
                        .toString();

                System.out.println("fullPATH" + fullPath);

                File destinationFile = new File(fullPath);

                file.setFilePath(fullPath);
                file.setFileType(extension);
                file.setActive(true);

                multipartFile.transferTo(destinationFile);

                fileRepository.save(file);
                FileDtoView fileDtoView = fileMapper.toDtoView(file);

                return CompletableFuture.completedFuture(fileDtoView);
            } else if (extension == null) {
                throw new FileNotFoundException("No File found");
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("File not found");
        }
        return null;
    }

}