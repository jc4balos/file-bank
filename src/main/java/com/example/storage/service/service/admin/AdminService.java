package com.example.storage.service.service.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    public Map<String, Object> deleteMultipleTrashFiles(HttpServletRequest request, Long[] folderIds, Long[] fileIds);

    public Map<String, Object> getAllTrashFiles(HttpServletRequest request);
}
