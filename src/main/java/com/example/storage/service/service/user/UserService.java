package com.example.storage.service.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.CreateUserDto;
import com.example.storage.service.dto.LoginDto;
import com.example.storage.service.dto.PasswordDto;
import com.example.storage.service.dto.UserDto;

@Service
public interface UserService {
    public CreateUserDto createUser(CreateUserDto userDto, HttpServletRequest request);

    public List<UserDto> getAllUsers(HttpServletRequest request);

    public UserDto modifyUser(Long userId, UserDto userDto, HttpServletRequest request);

    public Map<String, String> deactivateUser(Long userId, HttpServletRequest request);

    public Map<String, String> activateUser(Long userId, HttpServletRequest request);

    public Map<String, Object> loginUser(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response);

    public Map<String, String> changePassword(PasswordDto passwordDto, HttpServletRequest request);

}
