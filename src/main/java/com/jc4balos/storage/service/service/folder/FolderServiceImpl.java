package com.jc4balos.storage.service.service.folder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.dto.FileDtoView;
import com.jc4balos.storage.service.dto.FolderDto;
import com.jc4balos.storage.service.dto.FolderDtoView;
import com.jc4balos.storage.service.exception.SessionNotFoundException;
import com.jc4balos.storage.service.mapper.FileMapper;
import com.jc4balos.storage.service.mapper.FolderMapper;
import com.jc4balos.storage.service.mapper.MessageMapper;
import com.jc4balos.storage.service.model.AccessLevel;
import com.jc4balos.storage.service.model.FileAccess;
import com.jc4balos.storage.service.model.Folder;
import com.jc4balos.storage.service.model.FolderAccess;
import com.jc4balos.storage.service.model.User;
import com.jc4balos.storage.service.repository.AccessLevelRepository;
import com.jc4balos.storage.service.repository.FileAccessRepository;
import com.jc4balos.storage.service.repository.FolderAccessRepository;
import com.jc4balos.storage.service.repository.FolderRepository;
import com.jc4balos.storage.service.repository.UserRepository;
import com.jc4balos.storage.service.service.folder_access.FolderAccessServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

        private final FolderRepository folderRepository;
        private final UserRepository userRepository;
        private final AccessLevelRepository accessLevelRepository;

        private final FolderAccessRepository folderAccessRepository;
        private final FileAccessRepository fileAccessRepository;

        @Autowired
        private FolderMapper folderMapper;

        @Autowired
        private FileMapper fileMapper;

        @Autowired
        private MessageMapper messageMapper;

        @Autowired
        private FolderAccessServiceImpl folderAccessServiceImpl;

        @Override
        public FolderDto createFolder(FolderDto folderDto, HttpServletRequest request) {

                try {
                        HttpSession session = request.getSession();
                        System.out.println("user Id requeting session" + session.getAttribute("userId"));
                        User owner = userRepository.findById((Long) session.getAttribute("userId"))
                                        .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
                        Folder folder = new Folder();

                        List<Long> accessLevelIds = accessLevelRepository.findAll().stream()
                                        .map(AccessLevel::getAccessLevelId)
                                        .collect(Collectors.toList());

                        folder.setFolderName(folderDto.getFolderName());
                        folder.setFolderDescription(folderDto.getFolderDescription());
                        folder.setOwner(owner);
                        folder.setActive(true);
                        if (folderDto.getFolderParentId() != null) {
                                folder.setFolderParentId(folderDto.getFolderParentId());
                        } else {
                                folder.setFolderParentId(0L);
                        }

                        folderRepository.save(folder);

                        Long folderId = folder.getFolderId();

                        for (Long accessLevelIdItem : accessLevelIds) {
                                folderAccessServiceImpl.addFolderAccess(folderId, accessLevelIdItem);
                        }

                        return folderDto;

                } catch (Exception e) {
                        if (e instanceof RuntimeException) {
                                throw new SessionNotFoundException("Session not found. Please login.");
                        } else {
                                throw e;
                        }
                }

        }

        // Access all files in the parentFolderId where view is enabled for current user
        @Override
        public Map<String, Object> getAllFiles(Long folderId, HttpServletRequest request) {

                try {
                        Map<String, Object> response = new HashMap<>();

                        HttpSession session = request.getSession();
                        Long userId = (Long) session.getAttribute("userId");
                        System.out.println(userId);

                        if (userId != null) {
                                Long userAccessLevelId = userRepository.findById(userId)
                                                .orElseThrow(() -> new IllegalArgumentException("User does not exist"))
                                                .getAccessLevelId();
                                List<FolderAccess> folders = folderAccessRepository
                                                .findFoldersByFolderParentIdAndAccessLevel(
                                                                folderId,
                                                                userAccessLevelId);
                                System.out.println(folders);

                                List<FolderDtoView> mappedFolders = folderMapper.toFolderDtoWithPerms(folders);

                                List<FileAccess> files = fileAccessRepository.findFilesByFolderParentIdAndAccessLevel(
                                                folderId,
                                                userAccessLevelId);

                                List<FileDtoView> mappedFiles = fileMapper.toDtoViewWithPerms(files);

                                response.put("folders", mappedFolders);
                                response.put("files", mappedFiles);
                                return response;
                        } else {

                                throw new SessionNotFoundException("Session not found. Please log in.");
                        }

                } catch (Exception e) {

                        throw e;
                }

        }

        @Override
        public Map<String, Object> searchFilesAndFolders(Long folderId, HttpServletRequest request, String search) {

                try {
                        HttpSession session = request.getSession();
                        Long userId = (Long) session.getAttribute("userId");
                        if (userId != null) {
                                Map<String, Object> response = new HashMap<>();

                                String fileToSearch = search;
                                String folderToSearch = search;

                                Long userAccessLevelId = userRepository.findById(userId)
                                                .orElseThrow(() -> new IllegalArgumentException("User does not exist"))
                                                .getAccessLevelId();

                                List<FolderAccess> folders = folderAccessRepository
                                                .searchFoldersByFolderParentIdAndAccessLevel(
                                                                userAccessLevelId,
                                                                folderId, folderToSearch);

                                List<FolderDtoView> mappedFolders = folderMapper.toFolderDtoWithPerms(folders);

                                List<FileAccess> files = fileAccessRepository.searchFilesByFolderParentIdAndAccessLevel(
                                                userAccessLevelId,
                                                folderId, fileToSearch);

                                List<FileDtoView> mappedFiles = fileMapper.toDtoViewWithPerms(files);

                                response.put("folders", mappedFolders);
                                response.put("files", mappedFiles);
                                return response;
                        } else {
                                throw new SessionNotFoundException("Session not found. Please log in.");
                        }

                } catch (Exception e) {
                        throw e;
                }

        }

        @Override
        public Map<String, Object> searchGlobal(HttpServletRequest request, String search) {

                try {
                        HttpSession session = request.getSession();
                        Long userId = (Long) session.getAttribute("userId");
                        if (userId != null) {
                                Map<String, Object> response = new HashMap<>();

                                String fileToSearch = search;
                                String folderToSearch = search;

                                Long userAccessLevelId = userRepository.findById(userId)
                                                .orElseThrow(() -> new IllegalArgumentException("User does not exist"))
                                                .getAccessLevelId();

                                List<FolderAccess> folders = folderAccessRepository
                                                .searchFoldersGlobal(
                                                                userAccessLevelId,
                                                                folderToSearch);

                                List<FolderDtoView> mappedFolders = folderMapper.toFolderDtoWithPerms(folders);

                                List<FileAccess> files = fileAccessRepository.searchFilesGlobal(
                                                userAccessLevelId,
                                                fileToSearch);

                                List<FileDtoView> mappedFiles = fileMapper.toDtoViewWithPerms(files);

                                response.put("folders", mappedFolders);
                                response.put("files", mappedFiles);
                                return response;
                        } else {
                                throw new SessionNotFoundException("Session not found. Please log in.");
                        }

                } catch (Exception e) {
                        throw e;
                }

        }

        @Override
        public FolderDtoView modifyFolder(Long folderId, HttpServletRequest request, FolderDto folderDto) {

                try {
                        HttpSession session = request.getSession();
                        Long userId = (Long) session.getAttribute("userId");
                        if (userId != null) {
                                Folder folder = folderRepository.findById(folderId)
                                                .orElseThrow(() -> new IllegalArgumentException(
                                                                "Folder does not exist"));

                                folder.setFolderName(folderDto.getFolderName());
                                folder.setFolderDescription(folderDto.getFolderDescription());

                                folderRepository.save(folder);

                                return folderMapper.toFolderDtoView(folder);
                        } else {
                                throw new SessionNotFoundException("Session not found. Please log in.");

                        }

                } catch (Exception e) {
                        throw e;
                }

        }

        @Override
        public Map<String, String> deleteFolder(Long folderId, HttpServletRequest request) {
                try {
                        HttpSession session = request.getSession();
                        Long userId = (Long) session.getAttribute("userId");
                        if (userId != null) {
                                Folder folder = folderRepository.findById(folderId)
                                                .orElseThrow(() -> new IllegalArgumentException(
                                                                "Folder does not exist"));

                                folder.setActive(false);

                                folderRepository.save(folder);

                                return messageMapper.mapMessage("Folder deleted successfully");

                        } else {
                                throw new SessionNotFoundException("Session not found. Please log in.");
                        }

                } catch (Exception e) {
                        throw e;

                }
        }

        @Override
        public FolderDtoView getFolder(Long folderId, HttpServletRequest request) {

                try {
                        HttpSession session = request.getSession();
                        Long userId = (Long) session.getAttribute("userId");
                        if (userId != null) {
                                Folder folder = folderRepository.findById(folderId)
                                                .orElseThrow(() -> new IllegalArgumentException(
                                                                "Folder does not exist"));

                                return folderMapper.toFolderDtoView(folder);
                        } else {
                                throw new SessionNotFoundException("Session not found. Please log in.");

                        }

                } catch (Exception e) {
                        throw e;
                }

        }
}
