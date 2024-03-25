package com.example.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storage.service.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM folder WHERE active = 0")
    List<Folder> findByActiveFalse();
}
