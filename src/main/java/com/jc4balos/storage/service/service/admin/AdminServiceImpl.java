package com.jc4balos.storage.service.service.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc4balos.storage.service.dto.FileDtoView;
import com.jc4balos.storage.service.dto.FolderDtoView;
import com.jc4balos.storage.service.exception.SessionNotFoundException;
import com.jc4balos.storage.service.mapper.FileMapper;
import com.jc4balos.storage.service.mapper.FolderMapper;
import com.jc4balos.storage.service.model.FileAccess;
import com.jc4balos.storage.service.model.FileData;
import com.jc4balos.storage.service.model.Folder;
import com.jc4balos.storage.service.model.FolderAccess;
import com.jc4balos.storage.service.repository.FileAccessRepository;
import com.jc4balos.storage.service.repository.FileRepository;
import com.jc4balos.storage.service.repository.FolderAccessRepository;
import com.jc4balos.storage.service.repository.FolderRepository;
import com.jc4balos.storage.service.service.logging.LoggingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;
    private final FolderAccessRepository folderAccessRepository;
    private final FileAccessRepository fileAccessRepository;

    @Autowired
    private FolderMapper folderMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    LoggingService loggingService;

    @Override
    @Transactional
    public Map<String, Object> deleteMultipleTrashFiles(HttpServletRequest request, Long[] folderIds, Long[] fileIds) {

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                if (fileIds != null) {
                    for (Long fileId : fileIds) {
                        // delete file
                        List<FileAccess> fileAccesses = fileAccessRepository.findByFileId(fileId)
                                .orElseThrow(() -> new IllegalArgumentException("File does not exist"));
                        fileAccessRepository.deleteAll(fileAccesses);

                        FileData file = fileRepository.findById(fileId)
                                .orElseThrow(() -> new IllegalArgumentException("File does not exist"));

                        String filePath = file.getFilePath();
                        loggingService.createLog((Long) session.getAttribute("userId"),
                                "permanently deleted " + file.getFileName());

                        try {
                            Files.delete(Paths.get(filePath));
                            fileRepository.delete(file);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to delete file");
                        }
                    }
                }

                if (folderIds != null) {
                    for (Long folderId : folderIds) {
                        // delete folder accesses
                        List<FolderAccess> folderAccesses = folderAccessRepository.findByFolderId(folderId)
                                .orElseThrow(() -> new IllegalArgumentException("Folder does not exist"));

                        folderAccessRepository.deleteAll(folderAccesses);

                        // delete folder
                        Folder folder = folderRepository.findById(folderId)
                                .orElseThrow(() -> new IllegalArgumentException("Folder does not exist"));

                        loggingService.createLog((Long) session.getAttribute("userId"),
                                "permanently deleted " + folder.getFolderName());

                        folderRepository.delete(folder);
                    }
                }

                // Return a response
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Folders and files deleted successfully");
                return response;
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, Object> getAllTrashFiles(HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                List<Folder> folders = folderRepository.findByActiveFalse();
                List<FileData> files = fileRepository.findByActiveFalse();

                List<FolderDtoView> folderDtoViews = folders.stream().map(folderMapper::toFolderDtoView)
                        .collect(Collectors.toList());
                List<FileDtoView> fileDtoViews = files.stream().map(fileMapper::toDtoView).collect(Collectors.toList());

                Map<String, Object> response = new HashMap<>();
                response.put("folders", folderDtoViews);
                response.put("files", fileDtoViews);
                return response;
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
