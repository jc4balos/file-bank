package com.jc4balos.storage.service.service.admin;

import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface AdminService {

    public Map<String, Object> deleteMultipleTrashFiles(HttpServletRequest request, Long[] folderIds, Long[] fileIds);

    public Map<String, Object> getAllTrashFiles(HttpServletRequest request);

    public void shutdown(HttpServletRequest request);
}
