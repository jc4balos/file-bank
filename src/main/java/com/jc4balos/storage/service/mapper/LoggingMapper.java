package com.jc4balos.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.jc4balos.storage.service.dto.LoggingDtoView;
import com.jc4balos.storage.service.model.Logs;

import lombok.Data;

@Data
@Component
public class LoggingMapper {

    public LoggingDtoView toLoggingDtoView(Logs eventLog) {
        LoggingDtoView loggingDtoView = new LoggingDtoView();
        loggingDtoView.setLogId(eventLog.getLogId());
        loggingDtoView.setUserFullName(eventLog.getUser().getFirstName() + " " + eventLog.getUser().getLastName());
        loggingDtoView.setTimestamp(eventLog.getTimeStamp());
        loggingDtoView.setUserName(eventLog.getUser().getUserName());
        return loggingDtoView;
    }

}
