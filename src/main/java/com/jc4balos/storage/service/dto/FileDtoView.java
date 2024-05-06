package com.jc4balos.storage.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FileDtoView {

    private Long fileId;
    private String fileName;
    private String description;
    private Long folderId;
    private String filePath;
    private String fileType;
    private String mimeType;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private String ownerFullName;
    private String ownerUserName;
    private Boolean allowModifyFile;
    private Boolean allowDeleteFile;
    private Boolean allowViewFile;

}
