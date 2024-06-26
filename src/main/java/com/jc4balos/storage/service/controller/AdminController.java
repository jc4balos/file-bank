package com.jc4balos.storage.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jc4balos.storage.service.exception.ApplicationExceptionHandler;
import com.jc4balos.storage.service.service.admin.AdminService;
import com.jc4balos.storage.service.service.logging.LoggingService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @DeleteMapping("/api/v1/admin/delete-multiple-trash-files")
    public ResponseEntity<?> deleteMultipleTrashFiles(HttpServletRequest request, @RequestParam Long[] folderIds,
            @RequestParam Long[] fileIds) {
        try {
            return new ResponseEntity<>(adminService.deleteMultipleTrashFiles(request, folderIds, fileIds),
                    HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @GetMapping("/api/v1/admin/get-all-trash-files")
    public ResponseEntity<?> getAllTrashFiles(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(adminService.getAllTrashFiles(request), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

    @GetMapping("/api/v1/logs/get-all-logs")
    public ResponseEntity<?> getMethodName(HttpServletRequest request, @RequestParam int pageNumber) {
        try {
            return new ResponseEntity<>(loggingService.getLogs(request, pageNumber), HttpStatus.OK);
        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

}
