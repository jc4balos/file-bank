package com.example.storage.service.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.storage.service.dto.LoginDto;
import com.example.storage.service.dto.PasswordDto;
import com.example.storage.service.dto.UserDto;
import com.example.storage.service.exception.CredentialsInvalidException;
import com.example.storage.service.exception.UserNameAlreadyExistsException;
import com.example.storage.service.mapper.MessageMapper;
import com.example.storage.service.mapper.UserMapper;
import com.example.storage.service.model.User;
import com.example.storage.service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final MessageMapper messageMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        try {
            User user = userMapper.toUser(userDto);
            userRepository.save(user);
            userDto.setUserId(user.getUserId());
            return userDto;
        } catch (DataIntegrityViolationException e) {
            throw new UserNameAlreadyExistsException("Username already exist");

        }

    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto modifyUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).get();
        userMapper.toUser(userDto);

        userRepository.save(user);
        return userDto;
    }

    @Override
    public Map<String, String> deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        user.setActive(false);
        String userName = user.getUserName();
        userRepository.save(user);
        return messageMapper.mapMessage("User " + userName + " deactivated");
    }

    @Override
    public Map<String, String> activateUser(Long userId) {
        User user = userRepository.findById(userId).get();
        user.setActive(true);
        String userName = user.getUserName();
        userRepository.save(user);
        return messageMapper.mapMessage("User " + userName + " activated");
    }

    @Override
    public Map<String, Object> loginUser(LoginDto loginDto) {
        User user = userRepository.findByUserName(loginDto.getUserName());

        if (user != null) {
            if (Objects.equals(user.getPassword(), loginDto.getPassword())) {
                Map<String, Object> result = new HashMap<>();
                String message = "Login successful";
                result.put("message", message);
                return result;
            } else {
                throw new CredentialsInvalidException("Invalid password");
            }
        } else {
            throw new CredentialsInvalidException("Invalid username");
        }
    }

    @Override
    public Map<String, String> changePassword(PasswordDto passwordDto) {
        User user = userRepository.findById(passwordDto.getUserId()).get();

        if (user.getPassword().equals(passwordDto.getOldPassword())) {
            user.setPassword(passwordDto.getNewPassword());
            userRepository.save(user);
            return messageMapper.mapMessage("Password changed successfully");
        } else {
            throw new CredentialsInvalidException("Invalid old password");
        }
    }
}