package com.jc4balos.storage.service.service.logging;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.LoggingDtoView;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface LoggingService {

    public void createLog(Long userId, String message);

    public List<LoggingDtoView> getLogs(HttpServletRequest request, int pageNumber);

}
