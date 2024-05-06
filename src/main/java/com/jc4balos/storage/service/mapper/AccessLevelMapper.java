package com.jc4balos.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.jc4balos.storage.service.dto.AccessLevelDtoView;
import com.jc4balos.storage.service.model.AccessLevel;

import lombok.Data;

@Data
@Component
public class AccessLevelMapper {

    public AccessLevelDtoView toAccessLevelDtoView(AccessLevel accessLevel) {

        AccessLevelDtoView accessLevelDtoView = new AccessLevelDtoView();
        accessLevelDtoView.setAccessLevelId(accessLevel.getAccessLevelId());
        accessLevelDtoView.setAccessLevelName(accessLevel.getAccessLevelName());
        return accessLevelDtoView;
    }

}
