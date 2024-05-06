package com.jc4balos.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.jc4balos.storage.service.dto.UserDto;
import com.jc4balos.storage.service.dto.UserDtoView;
import com.jc4balos.storage.service.model.AccessLevel;
import com.jc4balos.storage.service.model.User;
import com.jc4balos.storage.service.repository.AccessLevelRepository;
import com.jc4balos.storage.service.repository.UserRepository;

import lombok.Data;

@Data
@Component
public class UserMapper {

    private final UserRepository userRepository;
    private final AccessLevelRepository accessLevelRepository;

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

    public UserDtoView toUserDtoView(User user) {
        UserDtoView userDtoView = new UserDtoView();
        Long userAccessLevelId = user.getAccessLevelId();
        AccessLevel accessLevel = accessLevelRepository.findById(userAccessLevelId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        String accessLevelName = accessLevel.getAccessLevelName();

        userDtoView.setUserId(user.getUserId());
        userDtoView.setFirstName(user.getFirstName());
        userDtoView.setMiddleName(user.getMiddleName());
        userDtoView.setLastName(user.getLastName());
        userDtoView.setUserName(user.getUserName());
        userDtoView.setAccessLevelId(userAccessLevelId);
        userDtoView.setAccessLevelName(accessLevelName);
        userDtoView.setActive(user.getActive());
        userDtoView.setTitle(user.getTitle());
        return userDtoView;
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