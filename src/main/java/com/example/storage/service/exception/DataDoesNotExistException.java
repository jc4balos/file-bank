package com.example.storage.service.exception;

import java.util.NoSuchElementException;

public class DataDoesNotExistException extends NoSuchElementException {
    DataDoesNotExistException(String message) {
        super(message);
    }
}
