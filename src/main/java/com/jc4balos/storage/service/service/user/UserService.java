package com.jc4balos.storage.service.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.CreateUserDto;
import com.jc4balos.storage.service.dto.LoginDto;
import com.jc4balos.storage.service.dto.PasswordDto;
import com.jc4balos.storage.service.dto.UserDto;
import com.jc4balos.storage.service.dto.UserDtoView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public interface UserService {
    public CreateUserDto createUser(CreateUserDto userDto, HttpServletRequest request);

    public List<UserDtoView> getAllUsers(HttpServletRequest request);

    public UserDto modifyUser(Long userId, UserDto userDto, HttpServletRequest request);

    public Map<String, String> deactivateUser(Long userId, HttpServletRequest request);

    public Map<String, String> activateUser(Long userId, HttpServletRequest request);

    public Map<String, Object> loginUser(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response);

    public Map<String, String> changePassword(PasswordDto passwordDto, HttpServletRequest request);

}
