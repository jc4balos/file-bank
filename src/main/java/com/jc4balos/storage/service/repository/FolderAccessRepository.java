package com.jc4balos.storage.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jc4balos.storage.service.model.FolderAccess;

public interface FolderAccessRepository extends JpaRepository<FolderAccess, Long> {

        @Query(nativeQuery = true, value = """
                        SELECT * FROM folder_access as b, folder as a, access_level as c
                        WHERE c.access_level_id = :accessLevelId
                        AND a.folder_parent_id= :folderParentId
                        AND c.access_level_id = b.access_level_id
                        AND b.folder_id = a.folder_id
                        AND b.allow_view_folder=1
                        AND a.active=1
                        """)
        List<FolderAccess> findFoldersByFolderParentIdAndAccessLevel(@Param("folderParentId") Long folderParentId,
                        @Param("accessLevelId") Long accessLevelId);

        @Query(nativeQuery = true, value = """
                        SELECT * FROM folder_access as b, folder as a, access_level as c
                        WHERE c.access_level_id = :accessLevelId
                        AND a.folder_parent_id= :folderParentId
                        AND c.access_level_id = b.access_level_id
                        AND b.folder_id = a.folder_id
                        AND b.allow_view_folder=1
                        AND a.active=1
                        AND a.folder_name LIKE %:search%
                        """)
        List<FolderAccess> searchFoldersByFolderParentIdAndAccessLevel(@Param("folderParentId") Long folderParentId,
                        @Param("accessLevelId") Long accessLevelId,
                        @Param("search") String search);

        @Query(nativeQuery = true, value = """
                        SELECT * FROM folder_access WHERE
                        access_level_id = :accessLevelId AND folder_id = :folderId
                                """)
        Optional<FolderAccess> findByFolderIdAndAccessLevelId(@Param("folderId") Long folderId,
                        @Param("accessLevelId") Long accessLevelId);

        @Query(nativeQuery = true, value = """
                        SELECT * FROM folder_access WHERE
                         folder_id = :folderId
                        """)
        Optional<List<FolderAccess>> findByFolderId(@Param("folderId") Long folderId);

}
