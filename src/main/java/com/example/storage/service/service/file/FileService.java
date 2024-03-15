package com.example.storage.service.service.file;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.FileDto;
import com.example.storage.service.dto.FileDtoView;

@Service
public interface FileService {

    public CompletableFuture<FileDtoView> createFile(FileDto fileDto); // Uploads file
    // Add folder on this function

    // public MultipartFile downloadFile(Long fileId); // Downloads file

    // public Map<String, Object> viewFile(Long fileId, HttpServletRequest request);
    // // View file
    // // contains {fileInfo:FileDto, file:multipartFile}

    public Map<String, String> deleteFile(Long fileId, Long userId); // Moves file to trash

    // public FileDto updateFile(Long fileId); // Updates file

    public FileDtoView restoreFile(Long fileId, Long userId); // Restores file from trash

    // public String deleteFileFromTrash(Long fileId); // Deletes file from trash

}