package com.epam.concurrency.hw6;

public class FileNotExistException extends RuntimeException {

    public FileNotExistException(String message) {
        super(message);
    }
}
