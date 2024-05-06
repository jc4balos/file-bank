package com.jc4balos.storage.service.dto;

import lombok.Data;

@Data
public class PasswordDto {

    private Long userId;
    private String oldPassword;
    private String newPassword;

}
