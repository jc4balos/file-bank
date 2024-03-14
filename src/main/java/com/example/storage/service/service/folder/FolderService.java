package com.example.storage.service.service.folder;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.FolderDto;

@Service
public interface FolderService {

    public FolderDto createFolder(FolderDto folderDto, Long userId);

    public Map<String, Object> getAllFiles(Long folderId, Long userId); // Where folder view

    public Map<String, Object> searchFilesAndFolders(Long folderId, Long userId, String search);
    // is enabled

    // public String deleteFolder(Long folderId, Long userId, HttpServletRequest
    // request);
    // // contains {folders:List<FolderDto>, files:List<FileDto>}

    // public FolderDto modifyFolder(Long folderId, String folderName, String
    // description);

    // public FolderDto restoreFolder(Long folderId, Long userId, HttpServletRequest
    // request);
}