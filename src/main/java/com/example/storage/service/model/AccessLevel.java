package com.example.storage.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
