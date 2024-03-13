package com.example.storage.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.storage.service.dto.FolderDto;
import com.example.storage.service.dto.FolderDtoView;
import com.example.storage.service.mapper.FolderMapper;
import com.example.storage.service.model.AccessLevel;
import com.example.storage.service.model.Folder;
import com.example.storage.service.model.FolderAccess;
import com.example.storage.service.model.User;
import com.example.storage.service.repository.AccessLevelRepository;
import com.example.storage.service.repository.FileRepository;
import com.example.storage.service.repository.FolderAccessRepository;
import com.example.storage.service.repository.FolderRepository;
import com.example.storage.service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final AccessLevelRepository accessLevelRepository;
    private final FileRepository fileRepository;
    private final FolderAccessRepository folderAccessRepository;

    @Autowired
    private FolderMapper folderMapper;

    @Autowired
    private FolderAccessServiceImpl folderAccessServiceImpl;

    @Override
    public FolderDto createFolder(FolderDto folderDto, Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        Folder folder = new Folder();
        Long accessLevelId = owner.getAccessLevelId();

        List<Long> accessLevelIds = accessLevelRepository.findAll().stream().map(AccessLevel::getAccessLevelId)
                .collect(Collectors.toList());

        folder.setFolderName(folderDto.getFolderName());
        folder.setFolderDescription(folderDto.getFolderDescription());
        folder.setOwner(owner);
        folder.setActive(true);
        if (folderDto.getFolderParentId() != null) {
            folder.setFolderParentId(folderDto.getFolderParentId());
        } else {
            folder.setFolderParentId(0L);
        }

        folderRepository.save(folder);

        Long folderId = folder.getFolderId();

        for (Long accessLevelIdItem : accessLevelIds) {
            folderAccessServiceImpl.addFolderAccess(folderId, accessLevelIdItem);
        }

        return folderDto;
    }

    // Access all files in the parentFolderId where view is enabled for current user
    @Override
    public Map<String, Object> getAllFiles(Long folderId, Long userId) {
        Map<String, Object> response = new HashMap<>();

        Long userAccessLevelId = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist")).getAccessLevelId();

        List<FolderAccess> folders = folderAccessRepository.findFoldersByFolderParentIdAndAccessLevel(
                userAccessLevelId,
                folderId);

        List<FolderDtoView> mappedFolders = folderMapper.toFolderDtoWithPerms(folders);

        // List<FileData> files =
        // fileRepository.findFilesByFolderParentIdAndAccessLevel(userAccessLevelId,
        // folderId);

        response.put("folders", mappedFolders);
        // response.put("files", files);
        return response;
    }
}
