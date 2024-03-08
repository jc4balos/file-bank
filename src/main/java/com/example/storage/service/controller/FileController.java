package com.example.storage.service.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.storage.service.dto.FileDto;
import com.example.storage.service.dto.FileDtoView;
import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.service.FileService;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @PostMapping("/api/v1/file/create-file")
    public ResponseEntity<?> uploadToFileSystem(@Valid @ModelAttribute FileDto fileDto,
            BindingResult bindingResult) {

        try {
            if (!bindingResult.hasErrors()) {
                CompletableFuture<FileDtoView> response = fileService.createFile(fileDto);
                if (response.isDone()) {
                    return new ResponseEntity<>(response.get(), HttpStatus.OK);
                }

                // if unsuccessfull return something nakalimutan ko lintek
                return new ResponseEntity<>("File upload failed", HttpStatus.BAD_REQUEST);
            } else {
                return applicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (IllegalArgumentException | InterruptedException | ExecutionException e) {
            return applicationExceptionHandler.handleCustomException(e);
        }

    }

}
