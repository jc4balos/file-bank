package com.example.storage.service.service;

import org.springframework.stereotype.Service;

@Service
public interface FolderAccessService {
    public String addFolderAccess(Long folderId, Long accessLevelId);

    // public List<?> modifyFolderAccess(Long userId, Long folderId, Long
    // accessLevelId, FolderAccessDto folderAccessDto);
}
