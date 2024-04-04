package com.example.storage.service.mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.example.storage.service.dto.SessionDto;
import com.example.storage.service.exception.SessionNotFoundException;

import lombok.Data;

@Data
@Component
public class SessionMapper {
    public SessionDto toDto(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            SessionDto sessionDto = new SessionDto();
            sessionDto.setUserFullName(session.getAttribute("userFullName").toString());
            sessionDto.setUserId((Long) session.getAttribute("userId"));
            sessionDto.setUserName(session.getAttribute("userName").toString());
            sessionDto.setAccessLevelId((Long) session.getAttribute("accessLevelId"));
            sessionDto.setUserTitle(session.getAttribute("userTitle").toString());
            return sessionDto;

        } catch (Exception e) {
            throw new SessionNotFoundException("Session not found. Please log in.");
        }

    }
}
