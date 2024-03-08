package com.example.storage.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.FolderDto;

@Service
public interface FolderService {

    public FolderDto createFolder(FolderDto folderDto, Long userId);

    public List<?> getAllFiles(Long folderId, Long userId); // Where folder view
    // is enabled

    // public String deleteFolder(Long folderId, Long userId, HttpServletRequest
    // request);
    // // contains {folders:List<FolderDto>, files:List<FileDto>}

    // public FolderDto modifyFolder(Long folderId, String folderName, String
    // description);

    // public FolderDto restoreFolder(Long folderId, Long userId, HttpServletRequest
    // request);
}