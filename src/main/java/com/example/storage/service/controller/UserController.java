package com.example.storage.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storage.service.dto.LoginDto;
import com.example.storage.service.dto.PasswordDto;
import com.example.storage.service.dto.UserDto;
import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.exception.CredentialsInvalidException;
import com.example.storage.service.service.user.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @PostMapping("/api/v1/user/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto,
            BindingResult bindingResult, HttpServletRequest request) {

        try {

            if (!bindingResult.hasErrors()) {
                return new ResponseEntity<>(userService.createUser(userDto, request), HttpStatus.OK);

            } else {
                return applicationExceptionHandler.handleBadRequest(bindingResult);

            }
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);

        }

    }

    @GetMapping("/api/v1/user/all-users")
    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
        try {

            return new ResponseEntity<>(userService.getAllUsers(request), HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/user/modify-user")
    public ResponseEntity<?> modifyUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult,
            HttpServletRequest request) {

        try {
            if (!bindingResult.hasErrors()) {
                return new ResponseEntity<>(userService.modifyUser(userDto.getUserId(), userDto, request),
                        HttpStatus.OK);
            } else {
                return applicationExceptionHandler.handleBadRequest(bindingResult);
            }

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @PostMapping("/api/v1/user/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto, HttpServletRequest request,
            HttpServletResponse response) {

        try {

            return new ResponseEntity<>(userService.loginUser(loginDto, request, response), HttpStatus.OK);
        } catch (CredentialsInvalidException e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/user/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordDto passwordDto, HttpServletRequest request) {

        try {
            return new ResponseEntity<>(
                    userService.changePassword(passwordDto, request),
                    HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @PatchMapping("/api/v1/user/deactivate-user")
    public ResponseEntity<?> deactivateUser(@RequestParam Long userId, HttpServletRequest request) {
        try {

            return new ResponseEntity<>(userService.deactivateUser(userId, request), HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/user/activate-user")
    public ResponseEntity<?> activateUser(@RequestParam Long userId, HttpServletRequest request) {
        try {
            return new ResponseEntity<>(userService.activateUser(userId, request), HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

}
