package com.example.storage.service.dto;

import lombok.Data;

@Data
public class SessionDto {
    private String userFullName;
    private Long userId;
    private String userName;
    private Long accessLevelId;
    private String userTitle;

}
