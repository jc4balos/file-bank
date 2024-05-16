package com.jc4balos.storage.service.service.access_level;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.AccessLevelDtoView;
import com.jc4balos.storage.service.dto.CreateAccessLevelDto;
import com.jc4balos.storage.service.exception.SessionNotFoundException;
import com.jc4balos.storage.service.mapper.AccessLevelMapper;
import com.jc4balos.storage.service.mapper.MessageMapper;
import com.jc4balos.storage.service.model.AccessLevel;
import com.jc4balos.storage.service.model.FileData;
import com.jc4balos.storage.service.model.Folder;
import com.jc4balos.storage.service.repository.AccessLevelRepository;
import com.jc4balos.storage.service.repository.FileRepository;
import com.jc4balos.storage.service.repository.FolderRepository;
import com.jc4balos.storage.service.service.file_access.FileAccessService;
import com.jc4balos.storage.service.service.folder_access.FolderAccessService;
import com.jc4balos.storage.service.service.logging.LoggingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessLevelServiceImpl implements AccessLevelService {

    private final AccessLevelRepository accessLevelRepository;
    private final AccessLevelMapper accessLevelMapper;
    private final MessageMapper messageMapper;
    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    private FileAccessService fileAccessService;

    @Autowired
    private FolderAccessService folderAccessService;

    @Override
    public Map<String, String> addAccessLevel(CreateAccessLevelDto createAccessLevelDto, HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                String accessLevelName = createAccessLevelDto.getAccessLevelName();
                AccessLevel accessLevel = new AccessLevel();
                accessLevel.setAccessLevelName(accessLevelName);
                accessLevel.setActive(true);
                accessLevelRepository.save(accessLevel);

                List<FileData> allFiles = fileRepository.findAll();
                for (FileData file : allFiles) {
                    fileAccessService.addFileAccess(file.getFileId(), accessLevel.getAccessLevelId());
                }

                List<Folder> allFolders = folderRepository.findAll();
                for (Folder folder : allFolders) {
                    folderAccessService.addFolderAccess(folder.getFolderId(), accessLevel.getAccessLevelId());
                }
                // loop to all folders then add folderaccesses
                // loop to all files then add file accessses

                loggingService.createLog((Long) session.getAttribute("userId"),
                        "added access level " + accessLevelName);
                return messageMapper.mapMessage("Access level " + accessLevel.getAccessLevelName() + " added");

            } else {

                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<AccessLevelDtoView> getAllAccessLevels(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                return accessLevelRepository.findByActiveAccessLevel().stream()
                        .map(accessLevelMapper::toAccessLevelDtoView)
                        .collect(Collectors.toList());
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");

            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AccessLevelDtoView> getDeactivatedAccessLevels(HttpServletRequest request) {
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                return accessLevelRepository.findByDeactivatedAccessLevel().stream()
                        .map(accessLevelMapper::toAccessLevelDtoView)
                        .collect(Collectors.toList());
            } else {
                throw new SessionNotFoundException("Session not found. Please log in.");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, String> deactivateAccessLevel(HttpServletRequest request, Long accessLevelId) {
        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).orElse(null);
                if (accessLevel != null) {
                    accessLevel.setActive(false);
                    accessLevelRepository.save(accessLevel);
                    loggingService.createLog((Long) session.getAttribute("userId"),
                            "deactivated access level " + accessLevel.getAccessLevelName());
                    return messageMapper
                            .mapMessage("Access level " + accessLevel.getAccessLevelName() + " deactivated");
                } else {
                    return messageMapper.mapMessage("Access level not found");
                }
            } else {

                throw new SessionNotFoundException("Session not found. Please log in.");

            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Map<String, String> restoreAccessLevel(HttpServletRequest request, Long accessLevelId) {

        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("userId") != null) {
                AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).orElse(null);
                if (accessLevel != null) {
                    accessLevel.setActive(true);
                    accessLevelRepository.save(accessLevel);
                    loggingService.createLog((Long) session.getAttribute("userId"),
                            "restored access level " + accessLevel.getAccessLevelName());
                    return messageMapper.mapMessage("Access level " + accessLevel.getAccessLevelName() + " restored");
                } else {
                    return messageMapper.mapMessage("Access level not found");
                }

            } else {

                throw new SessionNotFoundException("Session not found. Please log in.");

            }

        } catch (Exception e) {
            throw e;
        }

    }

}
