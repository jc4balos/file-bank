package com.example.storage.service.service.folder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.FolderDto;
import com.example.storage.service.dto.FolderDtoView;

@Service
public interface FolderService {

    public FolderDto createFolder(FolderDto folderDto, HttpServletRequest request);

    public Map<String, Object> getAllFiles(Long folderId, HttpServletRequest request); // Where folder view

    public Map<String, Object> searchFilesAndFolders(Long folderId, HttpServletRequest request, String search);
    // is enabled

    public Map<String, String> deleteFolder(Long folderId, HttpServletRequest request);
    // // contains {folders:List<FolderDto>, files:List<FileDto>}

    public FolderDtoView modifyFolder(Long folderId, HttpServletRequest request, FolderDto folderDto);

    // public FolderDto restoreFolder(Long folderId, Long userId, HttpServletRequest
    // request);
}