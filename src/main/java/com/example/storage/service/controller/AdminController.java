package com.example.storage.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storage.service.exception.ApplicationExceptionHandler;
import com.example.storage.service.service.admin.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @DeleteMapping("/api/v1/admin/delete-multiple-trash-files")
    public ResponseEntity<?> deleteMultipleTrashFiles(@RequestParam Long[] folderIds, @RequestParam Long[] fileIds) {
        try {
            return new ResponseEntity<>(adminService.deleteMultipleTrashFiles(folderIds, fileIds), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @GetMapping("/api/v1/admin/get-all-trash-files")
    public ResponseEntity<?> getAllTrashFiles() {
        try {
            return new ResponseEntity<>(adminService.getAllTrashFiles(), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

}
