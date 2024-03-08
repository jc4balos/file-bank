package com.example.storage.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FolderDtoView {

    private Long folderId;
    private String folderName;
    private String folderDescription;
    private Long folderParentId;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Long ownerId;
    private String ownerFirstName;
    private String ownerMiddleName;
    private String ownerLastName;
    private String ownerUsername;
    private Boolean allowAddFolder;
    private Boolean allowModifyFolder;
    private Boolean allowDeleteFolder;
    private Boolean allowViewFolder;
    private Boolean allowAddFile;
    private Boolean allowModifyFile;
    private Boolean allowDeleteFile;
    private Boolean allowViewFile;

}
