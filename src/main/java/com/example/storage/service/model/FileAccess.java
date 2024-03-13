package com.example.storage.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file_access")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "file_access_id")
    private Long folderAccessId;

    @ManyToOne(targetEntity = FileData.class)
    @JoinColumn(nullable = false, name = "file_id")
    private FileData fileData;

    @ManyToOne(targetEntity = AccessLevel.class)
    @JoinColumn(nullable = false, name = "access_level_id")
    private AccessLevel accessLevel;

    @Column(nullable = false, name = "allow_modify_file")
    private Boolean allowModifyFile;

    @Column(nullable = false, name = "allow_delete_file")
    private Boolean allowDeleteFile;

    @Column(nullable = false, name = "allow_view_file")
    private Boolean allowViewFile;
}
