package com.example.storage.service.service.permissions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.FileDtoView;
import com.example.storage.service.dto.FolderDtoView;
import com.example.storage.service.mapper.FileMapper;
import com.example.storage.service.mapper.FolderMapper;
import com.example.storage.service.model.FileAccess;
import com.example.storage.service.model.FolderAccess;
import com.example.storage.service.repository.FileAccessRepository;
import com.example.storage.service.repository.FolderAccessRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionsServiceImpl implements PermissionsService {

    private final FolderAccessRepository folderAccessRepository;
    private final FileAccessRepository fileAccessRepository;

    @NonNull
    private final FolderMapper folderMapper;

    @NonNull
    private final FileMapper fileMapper;

    @Override
    public Map<String, Object> allowCreateFolder(Long folderId, Long userId, Long accessLevelId) {

        FolderAccess folderAccess = folderAccessRepository.findByFolderIdAndAccessLevelId(folderId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Folder does not exist"));

        if (folderAccess.getAllowAddFolder() == true) {
            folderAccess.setAllowAddFolder(false);
        } else {
            folderAccess.setAllowAddFolder(true);
        }
        folderAccessRepository.save(folderAccess);
        Map<String, Object> response = new HashMap<>();
        FolderDtoView folderDtoView = folderMapper.toFolderDtoWithPerms(folderAccess);
        response.put("folder", folderDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }

    @Override
    public Map<String, Object> allowModifyFolder(Long folderId, Long userId, Long accessLevelId) {

        FolderAccess folderAccess = folderAccessRepository.findByFolderIdAndAccessLevelId(folderId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Folder does not exist"));

        if (folderAccess.getAllowModifyFolder() == true) {
            folderAccess.setAllowModifyFolder(false);
        } else {
            folderAccess.setAllowModifyFolder(true);
        }
        folderAccessRepository.save(folderAccess);
        Map<String, Object> response = new HashMap<>();
        FolderDtoView folderDtoView = folderMapper.toFolderDtoWithPerms(folderAccess);
        response.put("folder", folderDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }

    @Override
    public Map<String, Object> allowDeleteFolder(Long folderId, Long userId, Long accessLevelId) {

        FolderAccess folderAccess = folderAccessRepository.findByFolderIdAndAccessLevelId(folderId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Folder does not exist"));

        if (folderAccess.getAllowDeleteFolder() == true) {
            folderAccess.setAllowDeleteFolder(false);
        } else {
            folderAccess.setAllowDeleteFolder(true);
        }
        folderAccessRepository.save(folderAccess);
        Map<String, Object> response = new HashMap<>();
        FolderDtoView folderDtoView = folderMapper.toFolderDtoWithPerms(folderAccess);
        response.put("folder", folderDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }

    @Override
    public Map<String, Object> allowViewFolder(Long folderId, Long userId, Long accessLevelId) {

        FolderAccess folderAccess = folderAccessRepository.findByFolderIdAndAccessLevelId(folderId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Folder does not exist"));

        if (folderAccess.getAllowViewFolder() == true) {
            folderAccess.setAllowViewFolder(false);
        } else {
            folderAccess.setAllowViewFolder(true);
        }
        folderAccessRepository.save(folderAccess);
        Map<String, Object> response = new HashMap<>();
        FolderDtoView folderDtoView = folderMapper.toFolderDtoWithPerms(folderAccess);
        response.put("folder", folderDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }

    @Override
    public Map<String, Object> allowAddFile(Long folderId, Long userId, Long accessLevelId) {

        FolderAccess folderAccess = folderAccessRepository.findByFolderIdAndAccessLevelId(folderId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Folder does not exist"));

        if (folderAccess.getAllowAddFile() == true) {
            folderAccess.setAllowAddFile(false);
        } else {
            folderAccess.setAllowAddFile(true);
        }
        folderAccessRepository.save(folderAccess);
        Map<String, Object> response = new HashMap<>();
        FolderDtoView folderDtoView = folderMapper.toFolderDtoWithPerms(folderAccess);
        response.put("folder", folderDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }

    @Override
    public Map<String, Object> allowModifyFile(Long fileId, Long userId, Long accessLevelId) {
        FileAccess fileAccess = fileAccessRepository.findByFileIdAndAccessLevelId(fileId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("File does not exist"));

        if (fileAccess.getAllowModifyFile() == true) {
            fileAccess.setAllowModifyFile(false);
        } else {
            fileAccess.setAllowModifyFile(true);
        }
        fileAccessRepository.save(fileAccess);
        Map<String, Object> response = new HashMap<>();
        FileDtoView fileDtoView = fileMapper.toDtoViewWithPerms(fileAccess);
        response.put("folder", fileDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }

    @Override
    public Map<String, Object> allowViewFile(Long fileId, Long userId, Long accessLevelId) {
        FileAccess fileAccess = fileAccessRepository.findByFileIdAndAccessLevelId(fileId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("File does not exist"));

        if (fileAccess.getAllowViewFile() == true) {
            fileAccess.setAllowViewFile(false);
        } else {
            fileAccess.setAllowViewFile(true);
        }
        fileAccessRepository.save(fileAccess);
        Map<String, Object> response = new HashMap<>();
        FileDtoView fileDtoView = fileMapper.toDtoViewWithPerms(fileAccess);
        response.put("folder", fileDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }

    @Override
    public Map<String, Object> allowDeleteFile(Long fileId, Long userId, Long accessLevelId) {
        FileAccess fileAccess = fileAccessRepository.findByFileIdAndAccessLevelId(fileId, accessLevelId)
                .orElseThrow(
                        () -> new IllegalArgumentException("File does not exist"));

        if (fileAccess.getAllowDeleteFile() == true) {
            fileAccess.setAllowDeleteFile(false);
        } else {
            fileAccess.setAllowDeleteFile(true);
        }
        fileAccessRepository.save(fileAccess);
        Map<String, Object> response = new HashMap<>();
        FileDtoView fileDtoView = fileMapper.toDtoViewWithPerms(fileAccess);
        response.put("folder", fileDtoView);
        response.put("message", "Modified Successfully");
        return response;
    }
}
