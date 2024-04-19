package com.example.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.example.storage.service.dto.CreateUserDto;
import com.example.storage.service.model.User;

import lombok.Data;

@Data
@Component
public class CreateUserMapper {

    public CreateUserDto toUserDto(User user) {
        CreateUserDto userDto = new CreateUserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setAccessLevelId(user.getAccessLevelId());
        userDto.setTitle(user.getTitle());
        return userDto;
    }

    public User toUser(CreateUserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setAccessLevelId(userDto.getAccessLevelId());
        user.setTitle(userDto.getTitle());
        return user;
    }
}