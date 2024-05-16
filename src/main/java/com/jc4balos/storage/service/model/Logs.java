package com.jc4balos.storage.service.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "log_id")
    private Long logId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false, length = 1000, name = "event")
    private String event;

    @CreationTimestamp
    @Column(nullable = false, name = "time_stamp")
    private LocalDateTime timeStamp;

}
