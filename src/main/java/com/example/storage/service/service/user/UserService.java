package com.example.storage.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.LoginDto;
import com.example.storage.service.dto.PasswordDto;
import com.example.storage.service.dto.UserDto;

@Service
public interface UserService {
    public UserDto createUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public UserDto modifyUser(Long userId, UserDto userDto);

    public String deactivateUser(Long userId);

    public String activateUser(Long userId);

    public Map<String, Object> loginUser(LoginDto loginDto);

    public String changePassword(PasswordDto passwordDto);

}
