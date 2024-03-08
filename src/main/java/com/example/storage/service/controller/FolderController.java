package com.example.storage.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storage.service.dto.FolderDto;
import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.service.FolderService;

@Controller
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    // revise this hide user id dont user request param
    @PostMapping("/api/v1/folder/create-folder")
    public ResponseEntity<?> createFolder(@RequestParam Long userId, @Valid @RequestBody FolderDto folderDto,
            BindingResult bindingResult) {

        try {

            if (!bindingResult.hasErrors()) {

                return new ResponseEntity<>(folderService.createFolder(folderDto, userId), HttpStatus.OK);

            } else {

                return applicationExceptionHandler.handleBadRequest(bindingResult);
            }

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @GetMapping("/api/v1/folder/get-all-files")
    public ResponseEntity<?> getAllFiles(@RequestParam Long folderId,
            @RequestParam Long userId) {

        return new ResponseEntity<>(folderService.getAllFiles(folderId, userId),
                HttpStatus.OK);

    }
}
