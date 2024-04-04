package com.example.storage.service.service.access_level;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.AccessLevelDtoView;
import com.example.storage.service.dto.CreateAccessLevelDto;
import com.example.storage.service.exception.SessionNotFoundException;
import com.example.storage.service.mapper.AccessLevelMapper;
import com.example.storage.service.mapper.MessageMapper;
import com.example.storage.service.model.AccessLevel;
import com.example.storage.service.repository.AccessLevelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessLevelServiceImpl implements AccessLevelService {

    private final AccessLevelRepository accessLevelRepository;
    private final AccessLevelMapper accessLevelMapper;
    private final MessageMapper messageMapper;

    @Override
    public Map<String, String> addAccessLevel(CreateAccessLevelDto createAccessLevelDto, HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") == null) {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

            String accessLevelName = createAccessLevelDto.getAccessLevelName();
            AccessLevel accessLevel = new AccessLevel();

            if (accessLevelName != null || !accessLevelName.isEmpty()) {
                accessLevel.setAccessLevelName(accessLevelName);
                accessLevel.setActive(true);
                accessLevelRepository.save(accessLevel);
                return messageMapper.mapMessage("Access level " + accessLevel.getAccessLevelName() + " added");
            } else {
                return messageMapper.mapMessage("Access level name cannot be empty");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<AccessLevelDtoView> getAllAccessLevels(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") == null) {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

            return accessLevelRepository.findByActiveAccessLevel().stream()
                    .map(accessLevelMapper::toAccessLevelDtoView)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<AccessLevelDtoView> getDeactivatedAccessLevels(HttpServletRequest request) {
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") == null) {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

            return accessLevelRepository.findByDeactivatedAccessLevel().stream()
                    .map(accessLevelMapper::toAccessLevelDtoView)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, String> deactivateAccessLevel(Long userId, Long accessLevelId) {
        AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).orElse(null);
        if (accessLevel != null) {
            accessLevel.setActive(false);
            accessLevelRepository.save(accessLevel);
            return messageMapper.mapMessage("Access level " + accessLevel.getAccessLevelName() + " deactivated");
        } else {
            return messageMapper.mapMessage("Access level not found");
        }
    }

    @Override
    public Map<String, String> restoreAccessLevel(Long userId, Long accessLevelId) {
        AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).orElse(null);
        if (accessLevel != null) {
            accessLevel.setActive(true);
            accessLevelRepository.save(accessLevel);
            return messageMapper.mapMessage("Access level " + accessLevel.getAccessLevelName() + " restored");
        } else {
            return messageMapper.mapMessage("Access level not found");
        }
    }

}
