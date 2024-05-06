package com.jc4balos.storage.service.controller;

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

import com.jc4balos.storage.service.dto.CreateAccessLevelDto;
import com.jc4balos.storage.service.exception.ApplicationExceptionHandler;
import com.jc4balos.storage.service.service.access_level.AccessLevelService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AccessLevelController {

    @Autowired
    private AccessLevelService accessLevelService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @PostMapping("/api/v1/access-level/create-access-level")
    public ResponseEntity<?> createAccessLevel(@Valid @RequestBody CreateAccessLevelDto createAccessLevelDto,
            HttpServletRequest request, BindingResult bindingResult) {
        System.out.println(createAccessLevelDto);

        try {

            if (!bindingResult.hasErrors()) {
                return new ResponseEntity<>(accessLevelService.addAccessLevel(createAccessLevelDto, request),
                        HttpStatus.OK);
            } else {
                return applicationExceptionHandler.handleBadRequest(bindingResult);
            }

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
    public ResponseEntity<?> deleteAccessLevel(HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(accessLevelService.deactivateAccessLevel(request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/access-level/restore-access-level")
    public ResponseEntity<?> restoreAccessLevel(HttpServletRequest request, @RequestParam Long accessLevelId) {
        return new ResponseEntity<>(accessLevelService.restoreAccessLevel(request, accessLevelId), HttpStatus.OK);
    }
}
