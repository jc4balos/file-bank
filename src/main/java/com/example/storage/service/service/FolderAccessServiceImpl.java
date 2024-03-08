package com.example.storage.service.service;

import org.springframework.stereotype.Service;

import com.example.storage.service.model.AccessLevel;
import com.example.storage.service.model.Folder;
import com.example.storage.service.model.FolderAccess;
import com.example.storage.service.repository.AccessLevelRepository;
import com.example.storage.service.repository.FolderAccessRepository;
import com.example.storage.service.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderAccessServiceImpl implements FolderAccessService {

    private final FolderAccessRepository folderAccessRepository;
    private final AccessLevelRepository accessLevelRepository;

    private final FolderRepository folderRepository;

    @Override
    public String addFolderAccess(Long folderId, Long accessLevelId) {
        FolderAccess folderAccess = new FolderAccess();

        Folder folder = folderRepository.findById(folderId).get();
        AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).get();

        folderAccess.setFolder(folder);
        folderAccess.setAccessLevel(accessLevel);
        folderAccess.setAllowAddFolder(true);
        folderAccess.setAllowModifyFolder(true);
        folderAccess.setAllowViewFolder(false);
        folderAccess.setAllowDeleteFolder(true);
        folderAccess.setAllowAddFile(true);
        folderAccess.setAllowModifyFile(true);
        folderAccess.setAllowViewFile(true);
        folderAccess.setAllowDeleteFile(true);

        folderAccessRepository.save(folderAccess);
        return "Access added for folder " + folder.getFolderName() + " with access level "
                + accessLevel.getAccessLevelName();
    }

}
