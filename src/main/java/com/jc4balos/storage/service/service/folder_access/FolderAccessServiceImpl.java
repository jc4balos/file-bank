package com.jc4balos.storage.service.service.folder_access;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.model.AccessLevel;
import com.jc4balos.storage.service.model.Folder;
import com.jc4balos.storage.service.model.FolderAccess;
import com.jc4balos.storage.service.repository.AccessLevelRepository;
import com.jc4balos.storage.service.repository.FolderAccessRepository;
import com.jc4balos.storage.service.repository.FolderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderAccessServiceImpl implements FolderAccessService {

    private final FolderAccessRepository folderAccessRepository;
    private final AccessLevelRepository accessLevelRepository;

    private final FolderRepository folderRepository;

    @Override
    public Map<String, String> addFolderAccess(Long folderId, Long accessLevelId) {
        FolderAccess folderAccess = new FolderAccess();

        Folder folder = folderRepository.findById(folderId).get();
        AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).get();

        folderAccess.setFolder(folder);
        folderAccess.setAccessLevel(accessLevel);
        folderAccess.setAllowAddFolder(true);
        folderAccess.setAllowModifyFolder(true);
        folderAccess.setAllowViewFolder(true);
        folderAccess.setAllowDeleteFolder(true);
        folderAccess.setAllowAddFile(true);

        folderAccessRepository.save(folderAccess);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Access added for folder " + folder.getFolderName() + " with access level "
                + accessLevel.getAccessLevelName());
        return response;

    }

}
