package com.example.storage.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateAccessLevelDto {
    @NotNull(message = "Access Level Name is required")
    @NotEmpty(message = "Access Level Name is required")
    private String accessLevelName;

}
