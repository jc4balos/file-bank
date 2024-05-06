package com.jc4balos.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jc4balos.storage.service.model.FileData;

public interface FileRepository extends JpaRepository<FileData, Long> {

    FileData findByFileName(String fileName);

    @Query(nativeQuery = true, value = "SELECT * FROM files WHERE active = 0")
    List<FileData> findByActiveFalse();
}
