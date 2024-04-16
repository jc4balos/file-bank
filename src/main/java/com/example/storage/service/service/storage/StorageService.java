package com.example.storage.service.service.storage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.StorageInfoDto;

@Service
public interface StorageService {
    public List<StorageInfoDto> getStorageInfo(HttpServletRequest request);
}
