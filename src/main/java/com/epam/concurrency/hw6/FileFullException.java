package com.epam.concurrency.hw6;

public class FileFullException extends RuntimeException {

    public FileFullException(String message) {
        super(message);
    }

    public FileFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
