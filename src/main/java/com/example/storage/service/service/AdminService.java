package com.example.storage.service.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.storage.service.dto.FileDto;
import com.example.storage.service.dto.FolderDto;

public interface AdminService {

    Map<String, Object> getTrash(Long userId, HttpServletRequest request);

    String restoreFiles(List<FolderDto> folders, List<FileDto> files, Long userId, HttpServletRequest request);

}
