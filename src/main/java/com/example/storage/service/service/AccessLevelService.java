package com.example.storage.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.CreateAccessLevelDto;
import com.example.storage.service.model.AccessLevel;

@Service
public interface AccessLevelService {

    public String addAccessLevel(CreateAccessLevelDto createAccessLevelDto);

    // public List<AccessLevel> getAccessLevelsForFolder();

    // public String deactivateAccessLevel(Long userId, Long accessLevelId);

    // public String activateAccessLevel(Long userId, Long accessLevelId);

}
