package com.jc4balos.storage.service.service.file_access;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.storage.service.model.AccessLevel;
import com.jc4balos.storage.service.model.FileAccess;
import com.jc4balos.storage.service.model.FileData;
import com.jc4balos.storage.service.repository.AccessLevelRepository;
import com.jc4balos.storage.service.repository.FileAccessRepository;
import com.jc4balos.storage.service.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileAccessServiceImpl implements FileAccessService {

    private final FileAccessRepository fileAccessRepository;
    private final AccessLevelRepository accessLevelRepository;

    private final FileRepository fileRepository;

    @Override
    public Map<String, String> addFileAccess(Long fileId, Long accessLevelId) {
        FileAccess fileAccess = new FileAccess();

        FileData file = fileRepository.findById(fileId).get();
        AccessLevel accessLevel = accessLevelRepository.findById(accessLevelId).get();

        fileAccess.setFileData(file);
        fileAccess.setAccessLevel(accessLevel);
        fileAccess.setAllowModifyFile(true);
        fileAccess.setAllowDeleteFile(true);
        fileAccess.setAllowViewFile(true);

        fileAccessRepository.save(fileAccess);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Access added for file " + file.getFileName() + " with access level "
                + accessLevel.getAccessLevelName());
        return response;
    }
}
