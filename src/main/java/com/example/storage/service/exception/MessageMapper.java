package com.example.storage.service.exception;

import java.util.HashMap;
import java.util.Map;

public class MessageMapper {

    public Map<String, String> mapMessage(String message) {
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        return map;
    }

}
