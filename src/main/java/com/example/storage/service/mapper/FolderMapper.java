package com.example.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.example.storage.service.dto.FolderDto;
import com.example.storage.service.dto.FolderDtoView;
import com.example.storage.service.model.Folder;

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
        folderDtoView.setOwner(folder.getOwner());
        return folderDtoView;
    }

    public Folder toFolder(FolderDto folderDto) {
        Folder folder = new Folder();
        folder.setFolderName(folderDto.getFolderName());
        folder.setFolderDescription(folderDto.getFolderDescription());
        folder.setFolderParentId(folderDto.getFolderParentId());
        return folder;
    }

}
