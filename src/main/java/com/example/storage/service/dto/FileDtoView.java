package com.example.storage.service.dto;

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
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Long ownerId;

}
