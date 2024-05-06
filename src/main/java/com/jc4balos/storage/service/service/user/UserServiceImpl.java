package com.jc4balos.storage.service.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.CreateUserDto;
import com.jc4balos.storage.service.dto.LoginDto;
import com.jc4balos.storage.service.dto.PasswordDto;
import com.jc4balos.storage.service.dto.UserDto;
import com.jc4balos.storage.service.dto.UserDtoView;
import com.jc4balos.storage.service.exception.CredentialsInvalidException;
import com.jc4balos.storage.service.exception.SessionNotFoundException;
import com.jc4balos.storage.service.exception.UserNameAlreadyExistsException;
import com.jc4balos.storage.service.mapper.CreateUserMapper;
import com.jc4balos.storage.service.mapper.MessageMapper;
import com.jc4balos.storage.service.mapper.UserMapper;
import com.jc4balos.storage.service.model.User;
import com.jc4balos.storage.service.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CreateUserMapper createUserMapper;

    private final MessageMapper messageMapper;

    @Override
    public CreateUserDto createUser(CreateUserDto userDto, HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                User user = createUserMapper.toUser(userDto);
                user.setActive(true);
                userRepository.save(user);
                userDto.setUserId(user.getUserId());
                return userDto;

            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new UserNameAlreadyExistsException("Username already exist");
            } else {
                throw e;

            }

        }

    }

    @Override
    public List<UserDtoView> getAllUsers(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                return userRepository.getAllActiveUsers().stream().map(userMapper::toUserDtoView)
                        .collect(Collectors.toList());
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public UserDto modifyUser(Long userId, UserDto userDto, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long sessionUserId = (Long) session.getAttribute("userId");
            if (sessionUserId != null) {
                User modifiedUser = userMapper.modifyUser(userDto, userId);
                userRepository.save(modifiedUser);
                return userDto;
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, String> deactivateUser(Long userId, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long sessionUserId = (Long) session.getAttribute("userId");
            if (sessionUserId != null) {
                User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
                user.setActive(false);
                String userName = user.getUserName();
                userRepository.save(user);
                return messageMapper.mapMessage("User " + userName + " deactivated");
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, String> activateUser(Long userId, HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            Long sessionUserId = (Long) session.getAttribute("userId");
            if (sessionUserId != null) {
                User user = userRepository.findById(userId).get();
                user.setActive(true);
                String userName = user.getUserName();
                userRepository.save(user);
                return messageMapper.mapMessage("User " + userName + " activated");
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");

            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, Object> loginUser(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        User user = userRepository.findByUserName(loginDto.getUserName());

        if (user != null) {
            if (Objects.equals(user.getPassword(), loginDto.getPassword())) {

                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userAccessLevel", user.getAccessLevelId());
                session.setAttribute("userFullName",
                        user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
                session.setAttribute("userTitle", user.getTitle());
                session.setAttribute("userName", user.getUserName());

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
    public Map<String, String> changePassword(PasswordDto passwordDto, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long sessionUserId = (Long) session.getAttribute("userId");
        if (sessionUserId != null) {
            User user = userRepository.findById(passwordDto.getUserId()).get();

            if (user.getPassword().equals(passwordDto.getOldPassword())) {
                user.setPassword(passwordDto.getNewPassword());
                userRepository.save(user);
                return messageMapper.mapMessage("Password changed successfully");
            } else {
                throw new CredentialsInvalidException("Invalid old password");
            }
        } else {
            throw new SessionNotFoundException("Session not found. Please log in.");
        }
    }

}
