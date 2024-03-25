package com.example.storage.service.service.admin;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    public Map<String, Object> deleteMultipleTrashFiles(Long[] folderIds, Long[] fileIds);

    public Map<String, Object> getAllTrashFiles();
}
