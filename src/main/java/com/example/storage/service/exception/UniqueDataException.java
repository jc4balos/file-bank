package com.example.storage.service.exception;

public class UniqueDataException extends RuntimeException {
    public UniqueDataException(String message) {
        super(message);
    }
}