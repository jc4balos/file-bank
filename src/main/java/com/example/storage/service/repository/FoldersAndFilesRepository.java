package com.example.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storage.service.model.FolderAccess;

public interface FoldersAndFilesRepository extends JpaRepository<FolderAccess, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM folder_access as b, folder as a, access_level as c\n" + //
            "WHERE c.access_level_id = ?2\n" + //
            "AND a.folder_parent_id= ?1\n" + //
            "AND c.access_level_id = b.access_level_id\n" + //
            "AND b.folder_id = a.folder_id\n" + //
            "AND b.allow_view_folder=1\n" + //
            "AND a.active=1;")
    List<FolderAccess> findByFolderParentIdAndAccessLevel(Long folderParentId, Long accessLevelId);
}
