package com.jc4balos.storage.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoggingDtoView {
    private LocalDateTime timestamp;
    private String userFullName;
    private String userName;
    private String eventMessage;
    private Long logId;
}
