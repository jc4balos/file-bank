package com.example.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storage.service.model.FileData;

public interface FileRepository extends JpaRepository<FileData, Long> {

    FileData findByFileName(String fileName);

    FileData findByFolderIdAndAccessLevel(Long folderId, Long accessLevelId);

}
