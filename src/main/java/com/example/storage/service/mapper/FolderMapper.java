package com.example.storage.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.storage.service.dto.FolderDto;
import com.example.storage.service.dto.FolderDtoView;
import com.example.storage.service.model.Folder;
import com.example.storage.service.model.FolderAccess;

import lombok.Data;

@Data
@Component
public class FolderMapper {

    public FolderDtoView toFolderDtoView(Folder folder) {
        FolderDtoView folderDtoView = new FolderDtoView();
        folderDtoView.setFolderId(folder.getFolderId());
        folderDtoView.setFolderName(folder.getFolderName());
        folderDtoView.setFolderDescription(folder.getFolderDescription());
        folderDtoView.setFolderParentId(folder.getFolderParentId());
        folderDtoView.setCreatedDateTime(folder.getCreatedDateTime());
        folderDtoView.setUpdatedDateTime(folder.getUpdatedDateTime());
        folderDtoView.setOwnerId(folder.getOwner().getUserId());
        folderDtoView.setOwnerFirstName(folder.getOwner().getFirstName());
        folderDtoView.setOwnerMiddleName(folder.getOwner().getMiddleName());
        folderDtoView.setOwnerLastName(folder.getOwner().getLastName());
        folderDtoView.setOwnerUsername(folder.getOwner().getUserName());
        return folderDtoView;
    }

    public Folder toFolder(FolderDto folderDto) {
        Folder folder = new Folder();
        folder.setFolderName(folderDto.getFolderName());
        folder.setFolderDescription(folderDto.getFolderDescription());
        folder.setFolderParentId(folderDto.getFolderParentId());
        return folder;
    }

    public List<FolderDtoView> toFolderDtoWithPerms(List<FolderAccess> folderList) {
        List<FolderDtoView> foldersListDtoView = new ArrayList<>();
        for (FolderAccess folder : folderList) {
            FolderDtoView folderDtoView = new FolderDtoView();
            folderDtoView.setFolderId(folder.getFolder().getFolderId());
            folderDtoView.setFolderName(folder.getFolder().getFolderName());
            folderDtoView.setFolderDescription(folder.getFolder().getFolderDescription());
            folderDtoView.setFolderParentId(folder.getFolder().getFolderParentId());
            folderDtoView.setCreatedDateTime(folder.getFolder().getCreatedDateTime());
            folderDtoView.setUpdatedDateTime(folder.getFolder().getUpdatedDateTime());
            folderDtoView.setOwnerId(folder.getFolder().getOwner().getUserId());
            folderDtoView.setOwnerFirstName(folder.getFolder().getOwner().getFirstName());
            folderDtoView.setOwnerMiddleName(folder.getFolder().getOwner().getMiddleName());
            folderDtoView.setOwnerLastName(folder.getFolder().getOwner().getLastName());
            folderDtoView.setOwnerUsername(folder.getFolder().getOwner().getUserName());
            folderDtoView.setAllowAddFolder(folder.getAllowAddFolder());
            folderDtoView.setAllowModifyFolder(folder.getAllowModifyFolder());
            folderDtoView.setAllowDeleteFolder(folder.getAllowDeleteFolder());
            folderDtoView.setAllowViewFolder(folder.getAllowViewFolder());
            foldersListDtoView.add(folderDtoView);
        }
        return foldersListDtoView;

    }

}
