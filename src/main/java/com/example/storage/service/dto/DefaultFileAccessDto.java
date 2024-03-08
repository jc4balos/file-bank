package com.example.storage.service.dto;

import lombok.Data;

@Data
public class DefaultFileAccessDto {
    private Boolean allowAddFile = false;
    private Boolean allowModifyFile = false;
    private Boolean allowViewFile = true;
    private Boolean allowDeleteFile = false;
}
