package com.example.storage.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.storage.service.model.FolderAccess;

public interface FolderAccessRepository extends JpaRepository<FolderAccess, Long> {

    @Query(nativeQuery = true, value = """
            SELECT * FROM folder_access as b, folder as a, access_level as c
            WHERE c.access_level_id = ?2
            AND a.folder_parent_id= ?1
            AND c.access_level_id = b.access_level_id
            AND b.folder_id = a.folder_id
            AND b.allow_view_folder=1
            AND a.active=1;
            """)
    List<FolderAccess> findFoldersByFolderParentIdAndAccessLevel(Long folderParentId, Long accessLevelId);

}
