package com.example.storage.service.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.LoginDto;
import com.example.storage.service.dto.PasswordDto;
import com.example.storage.service.dto.UserDto;

@Service
public interface UserService {
    public UserDto createUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public UserDto modifyUser(Long userId, UserDto userDto);

    public Map<String, String> deactivateUser(Long userId);

    public Map<String, String> activateUser(Long userId);

    public Map<String, Object> loginUser(LoginDto loginDto, HttpServletRequest request);

    public Map<String, String> changePassword(PasswordDto passwordDto);

}
