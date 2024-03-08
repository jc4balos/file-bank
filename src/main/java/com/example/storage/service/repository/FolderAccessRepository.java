package com.example.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storage.service.model.FolderAccess;

public interface FolderAccessRepository extends JpaRepository<FolderAccess, Long> {

}
