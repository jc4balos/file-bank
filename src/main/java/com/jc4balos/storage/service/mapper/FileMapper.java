package com.jc4balos.storage.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jc4balos.storage.service.dto.FileDto;
import com.jc4balos.storage.service.dto.FileDtoView;
import com.jc4balos.storage.service.model.FileAccess;
import com.jc4balos.storage.service.model.FileData;
import com.jc4balos.storage.service.model.Folder;
import com.jc4balos.storage.service.model.User;
import com.jc4balos.storage.service.repository.FolderRepository;
import com.jc4balos.storage.service.repository.UserRepository;

import lombok.Data;

@Data
@Component
public class FileMapper {

    private final String folderPath = "D:\\Documents\\Repositories\\FileBank\\Storage\\";
    private final UserRepository userRepository;
    private final FolderRepository folderRepository;

    public FileData toFile(FileDto fileDto, Long ownerId) {
        User owner = userRepository.findById(ownerId).get();
        Folder folder = folderRepository.findById(fileDto.getFolderId()).get();

        if (owner != null) {
            FileData file = new FileData();
            file.setFileName(fileDto.getFileName());
            file.setDescription(fileDto.getDescription());
            file.setFolder(folder);

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
        fileDtoView.setMimeType(fileData.getMimeType());
        fileDtoView
                .setOwnerFullName(fileData.getOwner().getFirstName() + " " + fileData.getOwner().getMiddleName() + " "
                        + fileData.getOwner().getLastName());
        fileDtoView.setOwnerUserName(fileData.getOwner().getUserName());
        return fileDtoView;
    }

    public List<FileDtoView> toDtoViewWithPerms(List<FileAccess> fileList) {

        List<FileDtoView> filesListDtoView = new ArrayList<>();
        for (FileAccess file : fileList) {
            FileDtoView fileDtoView = new FileDtoView();
            fileDtoView.setFileId(file.getFileData().getFileId());
            fileDtoView.setFileName(file.getFileData().getFileName());
            fileDtoView.setDescription(file.getFileData().getDescription());
            fileDtoView.setFolderId(file.getFileData().getFolder().getFolderId());
            fileDtoView.setFilePath(file.getFileData().getFilePath());
            fileDtoView.setFileType(file.getFileData().getFileType());
            fileDtoView.setMimeType(file.getFileData().getMimeType());
            fileDtoView.setCreatedDateTime(file.getFileData().getCreatedDateTime());
            fileDtoView.setUpdatedDateTime(file.getFileData().getUpdatedDateTime());
            fileDtoView.setOwnerFullName(file.getFileData().getOwner().getFirstName() + " "
                    + file.getFileData().getOwner().getMiddleName() + " "
                    + file.getFileData().getOwner().getLastName());
            fileDtoView.setOwnerUserName(file.getFileData().getOwner().getUserName());
            fileDtoView.setAllowModifyFile(file.getAllowModifyFile());
            fileDtoView.setAllowDeleteFile(file.getAllowDeleteFile());
            fileDtoView.setAllowViewFile(file.getAllowViewFile());
            filesListDtoView.add(fileDtoView);

        }
        return filesListDtoView;
    }

    public FileDtoView toDtoViewWithPerms(FileAccess file) {

        FileDtoView fileDtoView = new FileDtoView();
        fileDtoView.setFileId(file.getFileData().getFileId());
        fileDtoView.setFileName(file.getFileData().getFileName());
        fileDtoView.setDescription(file.getFileData().getDescription());
        fileDtoView.setFolderId(file.getFileData().getFolder().getFolderId());
        fileDtoView.setFilePath(file.getFileData().getFilePath());
        fileDtoView.setFileType(file.getFileData().getFileType());
        fileDtoView.setMimeType(file.getFileData().getMimeType());
        fileDtoView.setCreatedDateTime(file.getFileData().getCreatedDateTime());
        fileDtoView.setUpdatedDateTime(file.getFileData().getUpdatedDateTime());
        fileDtoView.setOwnerFullName(file.getFileData().getOwner().getFirstName() + " "
                + file.getFileData().getOwner().getMiddleName() + " "
                + file.getFileData().getOwner().getLastName());
        fileDtoView.setOwnerUserName(file.getFileData().getOwner().getUserName());
        fileDtoView.setAllowModifyFile(file.getAllowModifyFile());
        fileDtoView.setAllowDeleteFile(file.getAllowDeleteFile());
        fileDtoView.setAllowViewFile(file.getAllowViewFile());

        return fileDtoView;
    }

}
