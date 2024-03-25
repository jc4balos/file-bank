package com.example.storage.service.service.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.storage.service.dto.FileDtoView;
import com.example.storage.service.dto.FolderDtoView;
import com.example.storage.service.mapper.FileMapper;
import com.example.storage.service.mapper.FolderMapper;
import com.example.storage.service.model.FileAccess;
import com.example.storage.service.model.FileData;
import com.example.storage.service.model.Folder;
import com.example.storage.service.model.FolderAccess;
import com.example.storage.service.repository.FileAccessRepository;
import com.example.storage.service.repository.FileRepository;
import com.example.storage.service.repository.FolderAccessRepository;
import com.example.storage.service.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;
    private final FolderAccessRepository folderAccessRepository;
    private final FileAccessRepository fileAccessRepository;

    @Autowired
    private FolderMapper folderMapper;

    @Autowired
    private FileMapper fileMapper;

    @Override
    @Transactional
    public Map<String, Object> deleteMultipleTrashFiles(Long[] folderIds, Long[] fileIds) {

        if (folderIds != null) {
            for (Long folderId : folderIds) {
                // delete folder accesses
                List<FolderAccess> folderAccesses = folderAccessRepository.findByFolderId(folderId)
                        .orElseThrow(() -> new IllegalArgumentException("Folder does not exist"));

                folderAccessRepository.deleteAll(folderAccesses);

                // delete folder
                Folder folder = folderRepository.findById(folderId)
                        .orElseThrow(() -> new IllegalArgumentException("Folder does not exist"));

                folderRepository.delete(folder);
            }
        }

        if (fileIds != null) {
            for (Long fileId : fileIds) {
                // delete file
                List<FileAccess> fileAccesses = fileAccessRepository.findByFileId(fileId)
                        .orElseThrow(() -> new IllegalArgumentException("File does not exist"));
                fileAccessRepository.deleteAll(fileAccesses);

                FileData file = fileRepository.findById(fileId)
                        .orElseThrow(() -> new IllegalArgumentException("File does not exist"));

                String filePath = file.getFilePath();

                try {
                    Files.delete(Paths.get(filePath));
                    fileRepository.delete(file);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to delete file");
                }
            }
        }

        // Return a response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Folders and files deleted successfully");
        return response;

    }

    @Override
    public Map<String, Object> getAllTrashFiles() {
        List<Folder> folders = folderRepository.findByActiveFalse();
        List<FileData> files = fileRepository.findByActiveFalse();

        List<FolderDtoView> folderDtoViews = folders.stream().map(folderMapper::toFolderDtoView)
                .collect(Collectors.toList());
        List<FileDtoView> fileDtoViews = files.stream().map(fileMapper::toDtoView).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("folders", folderDtoViews);
        response.put("files", fileDtoViews);
        return response;
    }
}
