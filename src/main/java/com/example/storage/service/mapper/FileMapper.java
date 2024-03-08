package com.example.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.example.storage.service.dto.FileDto;
import com.example.storage.service.dto.FileDtoView;
import com.example.storage.service.model.FileData;
import com.example.storage.service.model.Folder;
import com.example.storage.service.model.User;
import com.example.storage.service.repository.FolderRepository;
import com.example.storage.service.repository.UserRepository;

import lombok.Data;

@Data
@Component
public class FileMapper {

    private final String folderPath = "D:\\Documents\\Repositories\\FileBank\\Storage\\";
    private final UserRepository userRepository;
    private final FolderRepository folderRepository;

    public FileData toFile(FileDto fileDto) {
        User owner = userRepository.findById(fileDto.getOwnerId()).get();
        Folder folder = folderRepository.findById(fileDto.getFolderId()).get();

        if (owner != null) {
            FileData file = new FileData();
            file.setFileName(fileDto.getFileName());
            file.setDescription(fileDto.getDescription());
            file.setFolder(folder);
            // get the multipartfile name and add id as salt at the service

            file.setOwner(owner);

            return file;
        } else {
            throw new IllegalArgumentException("User not found");
        }

    }

    public FileDtoView toDtoView(FileData fileData) {
        FileDtoView fileDtoView = new FileDtoView();
        fileDtoView.setFileId(fileData.getFileId());
        fileDtoView.setFileName(fileData.getFileName());
        fileDtoView.setDescription(fileData.getDescription());
        fileDtoView.setFolderId(fileData.getFolder().getFolderId());
        fileDtoView.setFilePath(fileData.getFilePath());
        fileDtoView.setFileType(fileData.getFileType());
        fileDtoView.setCreatedDateTime(fileData.getCreatedDateTime());
        fileDtoView.setUpdatedDateTime(fileData.getUpdatedDateTime());
        fileDtoView.setOwnerId(fileData.getOwner().getUserId());
        return fileDtoView;
    }

}
