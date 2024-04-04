package com.example.storage.service.controller;

import javax.servlet.http.HttpServletRequest;

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
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowCreateFolder(folderId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-modify-folder")
    public ResponseEntity<?> allowModifyFolder(@RequestParam Long folderId,
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowModifyFolder(folderId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-delete-folder")
    public ResponseEntity<?> allowDeleteFolder(@RequestParam Long folderId,
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowDeleteFolder(folderId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-view-folder")
    public ResponseEntity<?> allowViewFolder(@RequestParam Long folderId,
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowViewFolder(folderId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-add-file")
    public ResponseEntity<?> allowAddFile(@RequestParam Long folderId,
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowAddFile(folderId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-modify-file")
    public ResponseEntity<?> allowModifyFile(@RequestParam Long fileId,
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowModifyFile(fileId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-view-file")
    public ResponseEntity<?> allowViewFile(@RequestParam Long fileId,
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowViewFile(fileId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/permissions/allow-delete-file")
    public ResponseEntity<?> allowDeleteFile(@RequestParam Long fileId,
            HttpServletRequest request, @RequestParam Long accessLevelId) {

        try {
            return new ResponseEntity<>(permissionsService.allowDeleteFile(fileId, request, accessLevelId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

}
