package com.jc4balos.storage.service.dto;

import lombok.Data;

@Data
public class UserDtoView {

    private Long userId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String userName;

    private Long accessLevelId;

    private String accessLevelName;

    private Boolean active;

    private String title;
}
