package com.jc4balos.storage.service.service.access_level;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.AccessLevelDtoView;
import com.jc4balos.storage.service.dto.CreateAccessLevelDto;

@Service
public interface AccessLevelService {

    public Map<String, String> addAccessLevel(CreateAccessLevelDto createAccessLevelDto, HttpServletRequest request);

    public List<AccessLevelDtoView> getAllAccessLevels(HttpServletRequest request);

    public List<AccessLevelDtoView> getDeactivatedAccessLevels(HttpServletRequest request);

    public Map<String, String> deactivateAccessLevel(HttpServletRequest request, Long accessLevelId);

    public Map<String, String> restoreAccessLevel(HttpServletRequest request, Long accessLevelId);

}
