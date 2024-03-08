package com.example.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storage.service.model.AccessLevel;

public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {

}