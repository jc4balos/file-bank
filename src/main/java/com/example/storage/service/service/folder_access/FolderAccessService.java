package com.example.storage.service.service.folder_access;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface FolderAccessService {
    public Map<String, String> addFolderAccess(Long folderId, Long accessLevelId);

    // public List<?> modifyFolderAccess(Long userId, Long folderId, Long
    // accessLevelId, FolderAccessDto folderAccessDto);
}
