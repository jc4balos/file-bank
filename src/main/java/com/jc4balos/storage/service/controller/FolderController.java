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

import com.jc4balos.storage.service.dto.FolderDto;
import com.jc4balos.storage.service.exception.ApplicationExceptionHandler;
import com.jc4balos.storage.service.service.folder.FolderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    // revise this hide user id dont user request param
    @PostMapping("/api/v1/folder/create-folder")
    public ResponseEntity<?> createFolder(HttpServletRequest request,
            @Valid @RequestBody FolderDto folderDto,
            BindingResult bindingResult) {

        try {

            if (!bindingResult.hasErrors()) {

                return new ResponseEntity<>(folderService.createFolder(folderDto, request), HttpStatus.OK);

            } else {

                return applicationExceptionHandler.handleBadRequest(bindingResult);
            }

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @GetMapping("/api/v1/folder/get-all-files")
    public ResponseEntity<?> getAllFiles(@RequestParam Long folderId,
            HttpServletRequest request) {
        try {

            return new ResponseEntity<>(folderService.getAllFiles(folderId, request),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @GetMapping("/api/v1/folder/get-folder")
    public ResponseEntity<?> getFolder(@RequestParam Long folderId,
            HttpServletRequest request) {
        try {

            return new ResponseEntity<>(folderService.getFolder(folderId, request),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @GetMapping("/api/v1/folder/get-files-and-folders/search")
    public ResponseEntity<?> searchFilesAndFolders(@RequestParam Long folderId,
            HttpServletRequest request, @RequestParam String search) {
        try {
            return new ResponseEntity<>(folderService.searchFilesAndFolders(folderId, request, search),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @PatchMapping("/api/v1/folder/modify-folder")
    public ResponseEntity<?> modifyFolder(@RequestParam Long folderId,
            HttpServletRequest request, @Valid @RequestBody FolderDto folderDto) {

        try {

            return new ResponseEntity<>(folderService.modifyFolder(folderId, request, folderDto),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @PatchMapping("/api/v1/folder/delete-folder")
    public ResponseEntity<?> deleteFolder(@RequestParam Long folderId,
            HttpServletRequest request) {

        try {
            return new ResponseEntity<>(folderService.deleteFolder(folderId, request),
                    HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

}
