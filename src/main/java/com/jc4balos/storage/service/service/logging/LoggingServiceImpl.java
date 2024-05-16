package com.jc4balos.storage.service.service.logging;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.LoggingDtoView;
import com.jc4balos.storage.service.exception.SessionNotFoundException;
import com.jc4balos.storage.service.mapper.LoggingMapper;
import com.jc4balos.storage.service.model.Logs;
import com.jc4balos.storage.service.model.User;
import com.jc4balos.storage.service.repository.LoggingRepository;
import com.jc4balos.storage.service.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoggingServiceImpl implements LoggingService {

    private final LoggingRepository loggingRepository;
    private final UserRepository userRepository;

    @Autowired
    private LoggingMapper loggingMapper;

    @Override
    public void createLog(Long userId, String messageLog) {
        User currentUser = userRepository.getUser(userId);
        Logs log = new Logs();
        log.setEvent(messageLog);
        log.setUser(currentUser);

        loggingRepository.save(log);

    }

    @Override
    public List<LoggingDtoView> getLogs(HttpServletRequest request, int pageNumber) {
        try {
            HttpSession session = request.getSession();
            userRepository.findById((Long) session.getAttribute("userId"))
                    .orElseThrow(() -> new IllegalArgumentException("Session not found. Please login"));

            Sort sort = Sort.by("timeStamp").descending();
            Page<Logs> logsList = loggingRepository.findAll(PageRequest.of(pageNumber, 10, sort));
            List<LoggingDtoView> loggingDtos = logsList.stream().map(loggingMapper::toLoggingDtoView)
                    .collect(Collectors.toList());

            return loggingDtos;

        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw new SessionNotFoundException("Session not found. Please login.");
            } else {
                throw e;
            }
        }

    }

}
