package com.jc4balos.storage.service.service.session;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.SessionDto;

@Service
public interface SessionService {
    public SessionDto getSessionInfo(HttpServletRequest request);
}
