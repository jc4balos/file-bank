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
@Table(name = "folder")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "folder_id")
    private Long folderId;

    @Column(nullable = false, length = 100, name = "folder_name")
    private String folderName;

    @Column(nullable = true, length = 2000, name = "folder_description")
    private String folderDescription;

    @Column(nullable = false, name = "folder_parent_id")
    private Long folderParentId;

    @CreationTimestamp
    @Column(nullable = false, name = "created_date_time")
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    @Column(nullable = false, name = "active")
    private Boolean active;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, name = "owner_id")
    private User owner;

}