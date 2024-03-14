package com.example.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storage.service.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {

}
