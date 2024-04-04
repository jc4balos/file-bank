package com.example.storage.service.service.permissions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface PermissionsService {
    public Map<String, Object> allowCreateFolder(Long folderId, HttpServletRequest request, Long accessLevelId);

    public Map<String, Object> allowModifyFolder(Long folderId, HttpServletRequest request, Long accessLevelId);

    public Map<String, Object> allowDeleteFolder(Long folderId, HttpServletRequest request, Long accessLevelId);

    public Map<String, Object> allowViewFolder(Long folderId, HttpServletRequest request, Long accessLevelId);

    public Map<String, Object> allowAddFile(Long folderId, HttpServletRequest request, Long accessLevelId);

    public Map<String, Object> allowModifyFile(Long fileId, HttpServletRequest request, Long accessLevelId);

    public Map<String, Object> allowViewFile(Long fileId, HttpServletRequest request, Long accessLevelId);

    public Map<String, Object> allowDeleteFile(Long fileId, HttpServletRequest request, Long accessLevelId);

}
