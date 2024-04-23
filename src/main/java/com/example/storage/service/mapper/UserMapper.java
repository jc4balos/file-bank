package com.example.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.example.storage.service.dto.UserDto;
import com.example.storage.service.model.User;
import com.example.storage.service.repository.UserRepository;

import lombok.Data;

@Data
@Component
public class UserMapper {

    private final UserRepository userRepository;

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setAccessLevelId(user.getAccessLevelId());
        userDto.setActive(user.getActive());
        userDto.setTitle(user.getTitle());
        return userDto;
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setAccessLevelId(userDto.getAccessLevelId());
        user.setActive(userDto.getActive());
        user.setTitle(userDto.getTitle());
        return user;
    }

    public User modifyUser(UserDto userDto, Long userId) {

        User user = userRepository.findById(userId).get();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setAccessLevelId(userDto.getAccessLevelId());
        user.setActive(userDto.getActive());
        user.setTitle(userDto.getTitle());
        return user;

    }
}