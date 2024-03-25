package com.example.storage.service.service.permissions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.FolderDtoView;
import com.example.storage.service.mapper.FolderMapper;
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
}
