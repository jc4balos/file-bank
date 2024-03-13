package com.example.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storage.service.model.Folder;
import com.example.storage.service.model.FolderAccess;

public interface FolderRepository extends JpaRepository<Folder, Long> {

}
