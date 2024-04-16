package com.example.storage.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.service.storage.StorageService;

@Controller
public class StorageController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @GetMapping("/api/v1/storage/info")
    public ResponseEntity<?> getStorageInfo(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(storageService.getStorageInfo(request), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);

        }
    }

}
