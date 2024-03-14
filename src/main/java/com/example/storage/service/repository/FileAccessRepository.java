package com.example.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.storage.service.model.FileAccess;

public interface FileAccessRepository extends JpaRepository<FileAccess, Long> {
    @Query(nativeQuery = true, value = """
            Select * from files as a, file_access as b, access_level as c
            WHERE c.access_level_id=:accessLevelId
            AND a.folder_id = :folderId
            AND b.access_level_id = c.access_level_id
            AND b.file_id = a.file_id
            AND b.allow_view_file=1
            """)
    List<FileAccess> findFilesByFolderParentIdAndAccessLevel(@Param("folderId") Long folderId,
            @Param("accessLevelId") Long accessLevelId);

    @Query(nativeQuery = true, value = """
            Select * from files as a, file_access as b, access_level as c
            WHERE c.access_level_id=:accessLevelId
            AND a.folder_id = :folderId
            AND b.access_level_id = c.access_level_id
            AND b.file_id = a.file_id
            AND b.allow_view_file=1
            AND a.file_name LIKE %:search%
            """)
    List<FileAccess> searchFilesByFolderParentIdAndAccessLevel(@Param("folderId") Long folderId,
            @Param("accessLevelId") Long accessLevelId, @Param("search") String search);

}
