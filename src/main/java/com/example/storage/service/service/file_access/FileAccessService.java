package com.example.storage.service.service.file_access;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface FileAccessService {

    public Map<String, String> addFileAccess(Long fileId, Long accessLevelId);

}
