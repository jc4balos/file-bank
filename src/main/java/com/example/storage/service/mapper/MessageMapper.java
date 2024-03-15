package com.example.storage.service.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MessageMapper {

    public Map<String, String> mapMessage(String message) {
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        return map;
    }

}
