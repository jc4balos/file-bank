package com.jc4balos.storage.service.service.logging;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.model.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface LoggingService {

    public Map<String, String> createLog(HttpServletRequest request, User user, String message);

}
