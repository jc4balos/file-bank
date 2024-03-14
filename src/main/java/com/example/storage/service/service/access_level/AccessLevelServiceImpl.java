package com.example.storage.service.service.access_level;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.AccessLevelDtoView;
import com.example.storage.service.dto.CreateAccessLevelDto;
import com.example.storage.service.mapper.AccessLevelMapper;
import com.example.storage.service.model.AccessLevel;
import com.example.storage.service.repository.AccessLevelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessLevelServiceImpl implements AccessLevelService {

    private final AccessLevelRepository accessLevelRepository;
    private final AccessLevelMapper accessLevelMapper;

    @Override
    public String addAccessLevel(CreateAccessLevelDto createAccessLevelDto) {

        String accessLevelName = createAccessLevelDto.getAccessLevelName();
        AccessLevel accessLevel = new AccessLevel();

        if (accessLevelName != null || !accessLevelName.isEmpty()) {
            accessLevel.setAccessLevelName(accessLevelName);
            accessLevel.setActive(true);
            accessLevelRepository.save(accessLevel);
            return "Access level " + accessLevelName + " added";
        } else {
            return "Access level name cannot be empty";
        }

    }

    @Override
    public List<AccessLevelDtoView> getAllAccessLevels() {
        return accessLevelRepository.findAll().stream()
                .map(accessLevelMapper::toAccessLevelDtoView)
                .collect(Collectors.toList());
    }

}
