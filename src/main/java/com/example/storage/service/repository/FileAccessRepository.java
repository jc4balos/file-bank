package com.example.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storage.service.model.FileAccess;

public interface FileAccessRepository extends JpaRepository<FileAccess, Long> {

}
