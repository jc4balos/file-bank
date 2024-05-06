package com.jc4balos.storage.service.controller;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jc4balos.storage.service.dto.FileDto;
import com.jc4balos.storage.service.dto.FileDtoView;
import com.jc4balos.storage.service.exception.ApplicationExceptionHandler;
import com.jc4balos.storage.service.service.file.FileService;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @PostMapping("/api/v1/file/create-file")
    public ResponseEntity<?> uploadToFileSystem(HttpServletRequest request, @Valid @ModelAttribute FileDto fileDto,
            BindingResult bindingResult) {

        try {
            if (!bindingResult.hasErrors()) {
                CompletableFuture<FileDtoView> response = fileService.createFile(request, fileDto);
                if (response.isDone()) {
                    return new ResponseEntity<>(response.get(), HttpStatus.OK);
                }

                // if unsuccessfull return something nakalimutan ko lintek
                return new ResponseEntity<>("File upload failed", HttpStatus.BAD_REQUEST);
            } else {
                return applicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

    @PatchMapping("/api/v1/file/delete-file")
    public ResponseEntity<?> deleteFile(@RequestParam Long fileId, HttpServletRequest request) {
        try {
            return new ResponseEntity<>(fileService.deleteFile(fileId, request), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/api/v1/file/restore-file")
    public ResponseEntity<?> restoreFile(@RequestParam Long fileId, HttpServletRequest request) {
        try {
            return new ResponseEntity<>(fileService.restoreFile(fileId, request), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @GetMapping("/api/v1/file/download-file")
    public ResponseEntity<?> downloadFile(@RequestParam Long fileId, HttpServletRequest request) {
        try {
            return new ResponseEntity<>(fileService.downloadFile(fileId, request), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }
}
