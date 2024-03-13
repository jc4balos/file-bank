package com.example.storage.service.service.file_access;

import org.springframework.stereotype.Service;

@Service
public interface FileAccessService {

    public String addFileAccess(Long fileId, Long accessLevelId);

}
