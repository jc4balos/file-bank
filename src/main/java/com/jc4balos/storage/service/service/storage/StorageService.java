package com.jc4balos.storage.service.service.storage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.StorageInfoDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface StorageService {
    public List<StorageInfoDto> getStorageInfo(HttpServletRequest request);
}
