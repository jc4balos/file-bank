package com.jc4balos.storage.service.service.session;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.SessionDto;
import com.jc4balos.storage.service.mapper.SessionMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;

    @Override
    public SessionDto getSessionInfo(HttpServletRequest request) {
        try {
            SessionDto sessionDto = sessionMapper.toDto(request);
            return sessionDto;

        } catch (Exception e) {
            throw e;
        }

    }

}
