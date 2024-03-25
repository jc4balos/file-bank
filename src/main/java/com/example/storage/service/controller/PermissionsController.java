package com.example.storage.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.service.permissions.PermissionsService;

@Controller
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @PatchMapping("/api/v1/permissions/allow-create-folder")
    public ResponseEntity<?> allowCreateFolder(@RequestParam Long folderId,
            @RequestParam Long userId, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowCreateFolder(folderId, userId, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-modify-folder")
    public ResponseEntity<?> allowModifyFolder(@RequestParam Long folderId,
            @RequestParam Long userId, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowModifyFolder(folderId, userId, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-delete-folder")
    public ResponseEntity<?> allowDeleteFolder(@RequestParam Long folderId,
            @RequestParam Long userId, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowDeleteFolder(folderId, userId, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-view-folder")
    public ResponseEntity<?> allowViewFolder(@RequestParam Long folderId,
            @RequestParam Long userId, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowViewFolder(folderId, userId, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

}
