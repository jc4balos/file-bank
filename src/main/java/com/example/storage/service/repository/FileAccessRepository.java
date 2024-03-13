package com.example.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storage.service.model.FileAccess;

public interface FileAccessRepository extends JpaRepository<FileAccess, Long> {
    @Query(nativeQuery = true, value = """
            Select * from files as a, file_access as b, access_level as c
            WHERE c.access_level_id=?2
            AND a.folder_id = ?1
            AND b.access_level_id = c.access_level_id
            AND b.file_id = a.file_id
            AND b.allow_view_file=1;
            """)
    List<FileAccess> findFilesByFolderParentIdAndAccessLevel(Long folderId, Long accessLevelId);
}
