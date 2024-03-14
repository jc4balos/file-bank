package com.example.storage.service.service.access_level;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.AccessLevelDtoView;
import com.example.storage.service.dto.CreateAccessLevelDto;

@Service
public interface AccessLevelService {

    public String addAccessLevel(CreateAccessLevelDto createAccessLevelDto);

    public List<AccessLevelDtoView> getAllAccessLevels();

    public String deactivateAccessLevel(Long userId, Long accessLevelId);

    public String restoreAccessLevel(Long userId, Long accessLevelId);

}
