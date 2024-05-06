package com.jc4balos.storage.service.mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.jc4balos.storage.service.dto.SessionDto;
import com.jc4balos.storage.service.exception.SessionNotFoundException;

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
            sessionDto.setAccessLevelId((Long) session.getAttribute("userAccessLevel"));
            sessionDto.setUserTitle(session.getAttribute("userTitle").toString());
            return sessionDto;

        } catch (Exception e) {
            throw new SessionNotFoundException("Session not found. Please log in.");
        }

    }
}
