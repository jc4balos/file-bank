package com.example.storage.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.storage.service.dto.CreateAccessLevelDto;
import com.example.storage.service.service.access_level.AccessLevelService;

@Controller
public class AccessLevelController {

    @Autowired
    private AccessLevelService accessLevelService;

    @PostMapping("/api/v1/access-level/create-access-level")
    public ResponseEntity<?> createAccessLevel(@Valid CreateAccessLevelDto createAccessLevelDto) {
        return new ResponseEntity<>(accessLevelService.addAccessLevel(createAccessLevelDto), HttpStatus.OK);
    }

    @GetMapping("/api/v1/access-level/get-access-levels")
    public ResponseEntity<?> getAccessLevels() {
        return new ResponseEntity<>(accessLevelService.getAllAccessLevels(), HttpStatus.OK);
    }
}
