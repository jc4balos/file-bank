package com.jc4balos.storage.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "folder_access")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FolderAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "folder_access_id")
    private Long folderAccessId;

    @ManyToOne(targetEntity = Folder.class)
    @JoinColumn(nullable = false, name = "folder_id")
    private Folder folder;

    @ManyToOne(targetEntity = AccessLevel.class)
    @JoinColumn(nullable = false, name = "access_level_id")
    private AccessLevel accessLevel;

    @Column(nullable = false, name = "allow_add_folder")
    private Boolean allowAddFolder;

    @Column(nullable = false, name = "allow_modify_folder")
    private Boolean allowModifyFolder;

    @Column(nullable = false, name = "allow_view_folder")
    private Boolean allowViewFolder;

    @Column(nullable = false, name = "allow_delete_folder")
    private Boolean allowDeleteFolder;

    @Column(nullable = false, name = "allow_add_file")
    private Boolean allowAddFile;

}
