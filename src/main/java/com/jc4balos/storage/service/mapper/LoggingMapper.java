package com.jc4balos.storage.service.mapper;

import org.springframework.stereotype.Component;

import com.jc4balos.storage.service.dto.LoggingDtoView;
import com.jc4balos.storage.service.model.Logs;
import com.jc4balos.storage.service.model.User;

import lombok.Data;

@Data
@Component
public class LoggingMapper {

    public LoggingDtoView toLoggingDtoView(User user, Logs eventLog) {
        LoggingDtoView loggingDtoView = new LoggingDtoView();
        loggingDtoView.setLogId(eventLog.getLogId());
        loggingDtoView.setUserFullName(user.getFirstName() + " " + user.getLastName());
        loggingDtoView.setTimestamp(eventLog.getTimeStamp());
        loggingDtoView.setUserName(user.getUserName());
        return loggingDtoView;
    }

}
