package com.jc4balos.storage.service.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "fileId")
    private Long fileId;

    @Column(nullable = false, length = 100, name = "fileName")
    private String fileName;

    @Column(nullable = false, length = 2000, name = "description")
    private String description;

    @ManyToOne(targetEntity = Folder.class)
    @JoinColumn(nullable = false, name = "file_folder_id")
    private Folder folder;

    @Column(nullable = false, name = "file_path")
    private String filePath;

    @Column(nullable = false, name = "file_type")
    private String fileType;

    @Column(nullable = false, name = "mime_type")
    private String mimeType;

    @CreationTimestamp
    @Column(nullable = false, name = "created_date_time")
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    @Column(nullable = false, name = "active")
    private Boolean active;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, name = "file_owner_id")
    private User owner;

}
