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
@Table(name = "file_access")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "file_access_id")
    private Long fileAccessId;

    @ManyToOne(targetEntity = FileData.class)
    @JoinColumn(nullable = false, name = "file_access_file_id")
    private FileData fileData;

    @ManyToOne(targetEntity = AccessLevel.class)
    @JoinColumn(nullable = false, name = "file_access_access_level_id")
    private AccessLevel accessLevel;

    @Column(nullable = false, name = "allow_modify_file")
    private Boolean allowModifyFile;

    @Column(nullable = false, name = "allow_delete_file")
    private Boolean allowDeleteFile;

    @Column(nullable = false, name = "allow_view_file")
    private Boolean allowViewFile;
}
