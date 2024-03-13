package com.example.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storage.service.model.FileData;

public interface FileRepository extends JpaRepository<FileData, Long> {

    FileData findByFileName(String fileName);

    // @Query(nativeQuery = true, value = """
    // SELECT * From files as a, folder as b, folder_access as c, access_level as d
    // WHERE c.allow_view_file=1
    // AND a.active = 1
    // AND a.folder_id = ?1
    // AND b.folder_id = a.folder_id
    // AND c.folder_id = b.folder_id
    // AND c.access_level_id = ?2
    // AND d.access_level_id = c.access_level_id;
    // """)
    // List<FileData> findFilesByFolderParentIdAndAccessLevel(Long folderId, Long
    // accessLevelId);

}
