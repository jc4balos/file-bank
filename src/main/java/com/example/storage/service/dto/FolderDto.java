package com.example.storage.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FolderDto {

    @NotNull(message = "Folder name is required")
    @Size(min = 2, message = "Folder name should have at least 2 characters")
    @Size(max = 100, message = "Folder name should have at most 100 characters")
    private String folderName;

    @Size(max = 2000, message = "Folder description is too long")
    private String folderDescription;

    private Long folderParentId;

}
