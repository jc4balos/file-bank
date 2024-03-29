package com.example.storage.service.service.permissions;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface PermissionsService {
    public Map<String, Object> allowCreateFolder(Long folderId, Long userId, Long accessLevelId);

    public Map<String, Object> allowModifyFolder(Long folderId, Long userId, Long accessLevelId);

    public Map<String, Object> allowDeleteFolder(Long folderId, Long userId, Long accessLevelId);

    public Map<String, Object> allowViewFolder(Long folderId, Long userId, Long accessLevelId);

    public Map<String, Object> allowAddFile(Long folderId, Long userId, Long accessLevelId);

    public Map<String, Object> allowModifyFile(Long fileId, Long userId, Long accessLevelId);

    public Map<String, Object> allowViewFile(Long fileId, Long userId, Long accessLevelId);

    public Map<String, Object> allowDeleteFile(Long fileId, Long userId, Long accessLevelId);

}
