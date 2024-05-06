package com.jc4balos.storage.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jc4balos.storage.service.exception.ApplicationExceptionHandler;
import com.jc4balos.storage.service.service.session.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ApplicationExceptionHandler applicationExceptionHandler;

    @GetMapping("/api/v1/session")
    public ResponseEntity<?> getSession(HttpServletRequest request, HttpServletResponse response) {
        try {
            return new ResponseEntity<>(sessionService.getSessionInfo(request), HttpStatus.OK);

        } catch (Exception e) {
            return applicationExceptionHandler.handleCustomException(e);
        }
    }

}
