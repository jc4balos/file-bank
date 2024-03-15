package com.example.storage.service.service.access_level;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.AccessLevelDtoView;
import com.example.storage.service.dto.CreateAccessLevelDto;

@Service
public interface AccessLevelService {

    public Map<String, String> addAccessLevel(CreateAccessLevelDto createAccessLevelDto);

    public List<AccessLevelDtoView> getAllAccessLevels();

    public List<AccessLevelDtoView> getDeactivatedAccessLevels();

    public Map<String, String> deactivateAccessLevel(Long userId, Long accessLevelId);

    public Map<String, String> restoreAccessLevel(Long userId, Long accessLevelId);

}
