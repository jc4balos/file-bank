package com.jc4balos.storage.service.service.session;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.SessionDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface SessionService {
    public SessionDto getSessionInfo(HttpServletRequest request);
}
