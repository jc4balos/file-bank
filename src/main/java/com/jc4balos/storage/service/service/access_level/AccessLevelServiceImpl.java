package com.jc4balos.storage.service.service.access_level;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.AccessLevelDtoView;
import com.jc4balos.storage.service.dto.CreateAccessLevelDto;
import com.jc4balos.storage.service.exception.SessionNotFoundException;
import com.jc4balos.storage.service.mapper.AccessLevelMapper;
import com.jc4balos.storage.service.mapper.MessageMapper;
import com.jc4balos.storage.service.model.AccessLevel;
import com.jc4balos.storage.service.repository.AccessLevelRepository;

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
            if (session.getAttribute("userId") != null) {
                String accessLevelName = createAccessLevelDto.getAccessLevelName();
                AccessLevel accessLevel = new AccessLevel();
                System.out.print(accessLevelName);
                accessLevel.setAccessLevelName(accessLevelName);
                accessLevel.setActive(true);
                accessLevelRepository.save(accessLevel);
                return messageMapper.mapMessage("Access level " + accessLevel.getAccessLevelName() + " added");

            } else {

                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<AccessLevelDtoView> getAllAccessLevels(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                return accessLevelRepository.findByActiveAccessLevel().stream()
                        .map(accessLevelMapper::toAccessLevelDtoView)
                        .collect(Collectors.toList());
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");

            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AccessLevelDtoView> getDeactivatedAccessLevels(HttpServletRequest request) {
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                return accessLevelRepository.findByDeactivatedAccessLevel().stream()
                        .map(accessLevelMapper::toAccessLevelDtoView)
                        .collect(Collectors.toList());
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, String> deactivateAccessLevel(HttpServletRequest request, Long accessLevelId) {
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).orElse(null);
                if (accessLevel != null) {
                    accessLevel.setActive(false);
                    accessLevelRepository.save(accessLevel);
                    return messageMapper
                            .mapMessage("Access level " + accessLevel.getAccessLevelName() + " deactivated");
                } else {
                    return messageMapper.mapMessage("Access level not found");
                }
            } else {

                throw new SessionNotFoundException("Session not found. Please log in.");

            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, String> restoreAccessLevel(HttpServletRequest request, Long accessLevelId) {

        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).orElse(null);
                if (accessLevel != null) {
                    accessLevel.setActive(true);
                    accessLevelRepository.save(accessLevel);
                    return messageMapper.mapMessage("Access level " + accessLevel.getAccessLevelName() + " restored");
                } else {
                    return messageMapper.mapMessage("Access level not found");
                }

            } else {

                throw new SessionNotFoundException("Session not found. Please log in.");

            }

        } catch (Exception e) {
            throw e;
        }

    }

}
