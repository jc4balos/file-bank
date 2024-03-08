package com.example.storage.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDto {

    @NotNull(message = "File name is required")
    @NotBlank(message = "File name is required")
    private String fileName;

    @Size(max = 2000, message = "File description is too long")
    private String description;

    @NotNull(message = "File is required")
    private MultipartFile multipartFile;

    @NotNull(message = "Folder id is required")
    private Long folderId;

    @NotNull(message = "Owner id is required")
    private Long ownerId;

}
