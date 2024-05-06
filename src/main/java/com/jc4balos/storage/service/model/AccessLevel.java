package com.jc4balos.storage.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "access_level")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "access_level_id")
    private Long accessLevelId;

    @Column(nullable = false, unique = true, length = 100, name = "access_level_name")
    private String accessLevelName;

    @Column(nullable = false, name = "active")
    private Boolean active;

}
