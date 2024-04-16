package com.example.storage.service.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.service.session.SessionService;

@Controller
public class StorageController {
@GetMapping("/api/v1/storage/info")
public ResponseEntity<?> getStorageInfo(HttpServletRequest request){


}
    

    
}
