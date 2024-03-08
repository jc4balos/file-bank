package com.example.storage.service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {

    private Long userId;

    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name is required")
    @Size(min = 2, message = "First name should have at least 2 characters")
    @Size(max = 100, message = "First name should have at most 100 characters")
    private String firstName;

    @Size(max = 100, message = "Middle name should have at most 100 characters")
    private String middleName;

    @NotEmpty(message = "Last name is required")
    @NotNull(message = "Last name is required")
    @Size(min = 2, message = "Last name should have at least 2 characters")
    @Size(max = 100, message = "Last name should have at most 100 characters")
    private String lastName;

    @NotEmpty(message = "User name is required")
    @NotNull(message = "User name is required")
    @Size(min = 2, message = "User name should have at least 2 characters")
    @Size(max = 100, message = "User name should have at most 100 characters")
    private String userName;

    @NotEmpty(message = "Password is required")
    @NotNull(message = "Password is required")
    private String password;

    @NotNull
    private Long accessLevelId;

    @NotNull
    private Boolean active;
}
