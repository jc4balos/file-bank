package com.jc4balos.storage.service.service.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.FileDto;
import com.jc4balos.storage.service.dto.FileDtoView;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface FileService {

    public CompletableFuture<FileDtoView> createFile(HttpServletRequest request, FileDto fileDto)
            throws FileNotFoundException, IOException; // Uploads file
    // Add folder on this function

    public InputStreamResource downloadFile(Long fileId, HttpServletRequest request); // Downloads file

    // public Map<String, Object> viewFile(Long fileId, HttpServletRequest request);
    // // View file
    // // contains {fileInfo:FileDto, file:multipartFile}

    public Map<String, String> deleteFile(Long fileId, HttpServletRequest request); // Moves file to trash

    // public FileDto updateFile(Long fileId); // Updates file

    public FileDtoView restoreFile(Long fileId, HttpServletRequest request); // Restores file from trash

    // public String deleteFileFromTrash(Long fileId); // Deletes file from trash

}