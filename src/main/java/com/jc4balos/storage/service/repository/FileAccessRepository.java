package com.jc4balos.storage.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jc4balos.storage.service.model.FileAccess;

public interface FileAccessRepository extends JpaRepository<FileAccess, Long> {
        @Query(nativeQuery = true, value = """
                        Select * from files as a, file_access as b, access_level as c
                        WHERE c.access_level_id= :accessLevelId
                        AND a.file_folder_id = :folderParentId
                        AND b.file_access_access_level_id = c.access_level_id
                        AND b.file_access_file_id = a.file_id
                        AND a.active= 1
                        AND b.allow_view_file=1
                        """)
        List<FileAccess> findFilesByFolderParentIdAndAccessLevel(@Param("folderParentId") Long folderId,
                        @Param("accessLevelId") Long accessLevelId);

        @Query(nativeQuery = true, value = """
                        Select * from files as a, file_access as b, access_level as c
                        WHERE c.access_level_id=:accessLevelId
                        AND a.file_folder_id = :folderParentId
                        AND b.file_access_access_level_id = c.access_level_id
                        AND b.file_access_file_id = a.file_id
                        AND b.allow_view_file=1
                        AND a.active =1
                        AND a.file_name LIKE %:search%
                        """)
        List<FileAccess> searchFilesByFolderParentIdAndAccessLevel(@Param("folderParentId") Long folderId,
                        @Param("accessLevelId") Long accessLevelId, @Param("search") String search);

        @Query(nativeQuery = true, value = """
                        Select * from files as a, file_access as b, access_level as c
                        WHERE c.access_level_id=:accessLevelId
                        AND b.file_access_access_level_id = c.access_level_id
                        AND b.file_access_file_id = a.file_id
                        AND b.allow_view_file=1
                        AND a.active =1
                        AND a.file_name LIKE %:search%
                        """)
        List<FileAccess> searchFilesGlobal(
                        @Param("accessLevelId") Long accessLevelId, @Param("search") String search);

        @Query(nativeQuery = true, value = """
                        SELECT * FROM file_access WHERE
                        access_level_id = :accessLevelId AND file_id = :fileId
                                """)
        Optional<FileAccess> findByFileIdAndAccessLevelId(@Param("fileId") Long fileId,
                        @Param("accessLevelId") Long accessLevelId);

        @Query(nativeQuery = true, value = """
                        SELECT * FROM file_access WHERE
                         file_id = :fileId
                        """)
        Optional<List<FileAccess>> findByFileId(@Param("fileId") Long fileId);
}
