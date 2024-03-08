package com.example.storage.service.service;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.CreateAccessLevelDto;
import com.example.storage.service.model.AccessLevel;
import com.example.storage.service.repository.AccessLevelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessLevelServiceImpl implements AccessLevelService {

    private final AccessLevelRepository accessLevelRepository;

    @Override
    public String addAccessLevel(CreateAccessLevelDto createAccessLevelDto) {

        String accessLevelName = createAccessLevelDto.getAccessLevelName();
        AccessLevel accessLevel = new AccessLevel();

        if (accessLevelName != null || !accessLevelName.isEmpty()) {
            accessLevel.setAccessLevelName(accessLevelName);
            accessLevelRepository.save(accessLevel);
            return "Access level " + accessLevelName + " added";
        } else {
            return "Access level name cannot be empty";
        }

    }

}
