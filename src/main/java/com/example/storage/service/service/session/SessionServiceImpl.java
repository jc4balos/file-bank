package com.example.storage.service.service.session;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.SessionDto;
import com.example.storage.service.mapper.SessionMapper;

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