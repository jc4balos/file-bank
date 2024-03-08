package com.example.storage.service.dto;

import lombok.Data;

@Data
public class DefaultFolderAccessDto {
    private Boolean allowAddFolder = false;
    private Boolean allowModifyFolder = false;
    private Boolean allowViewFolder = true;
    private Boolean allowDeleteFolder = false;
}
