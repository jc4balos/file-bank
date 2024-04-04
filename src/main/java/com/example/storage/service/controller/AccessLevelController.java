package com.example.storage.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storage.service.dto.CreateAccessLevelDto;
import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.service.access_level.AccessLevelService;

@Controller
public class AccessLevelController {

    @Autowired
    private AccessLevelService accessLevelService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @PostMapping("/api/v1/access-level/create-access-level")
    public ResponseEntity<?> createAccessLevel(@Valid CreateAccessLevelDto createAccessLevelDto,
            HttpServletRequest request) {
        try {
            return new ResponseEntity<>(accessLevelService.addAccessLevel(createAccessLevelDto, request),
                    HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @GetMapping("/api/v1/access-level/get-access-levels")
    public ResponseEntity<?> getAccessLevels(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(accessLevelService.getAllAccessLevels(request), HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @GetMapping("/api/v1/access-level/get-deactivated-access-levels")
    public ResponseEntity<?> getDeactivatedAccessLevels(HttpServletRequest request) {
        try {

            return new ResponseEntity<>(accessLevelService.getDeactivatedAccessLevels(request), HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/access-level/delete-access-level")
    public ResponseEntity<?> deleteAccessLevel(@RequestParam Long userId, @RequestParam Long accessLevelId) {
        return new ResponseEntity<>(accessLevelService.deactivateAccessLevel(userId, accessLevelId), HttpStatus.OK);
    }

    @PatchMapping("/api/v1/access-level/restore-access-level")
    public ResponseEntity<?> restoreAccessLevel(@RequestParam Long userId, @RequestParam Long accessLevelId) {
        return new ResponseEntity<>(accessLevelService.restoreAccessLevel(userId, accessLevelId), HttpStatus.OK);
    }
}
