package com.example.storage.service.service.session;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.SessionDto;

@Service
public interface SessionService {
    public SessionDto getSessionInfo(HttpServletRequest request);
}
