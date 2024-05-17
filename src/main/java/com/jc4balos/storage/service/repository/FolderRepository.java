package com.jc4balos.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jc4balos.storage.service.model.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM folder WHERE active = 0")
    List<Folder> findByActiveFalse();

    @Query(nativeQuery = true, value = "SELECT * FROM folder WHERE active = 1 AND folder_parent_id= :folderParentId")
    List<Folder> findByActiveTrueAndFolderParentId(@Param("folderParentId") Long folderParentId);

}
