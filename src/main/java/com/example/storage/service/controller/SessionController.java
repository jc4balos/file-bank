package com.example.storage.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.storage.service.service.session.SessionService;

@Controller
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/api/v1/session")
    public ResponseEntity<?> getSession(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(sessionService.getSessionInfo(request), HttpStatus.OK);
    }

}
