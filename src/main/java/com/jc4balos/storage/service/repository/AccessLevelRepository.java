package com.jc4balos.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jc4balos.storage.service.model.AccessLevel;

public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM access_level WHERE active = 0")
    public List<AccessLevel> findByDeactivatedAccessLevel();

    @Query(nativeQuery = true, value = "SELECT * FROM access_level WHERE active = 1")
    public List<AccessLevel> findByActiveAccessLevel();

}