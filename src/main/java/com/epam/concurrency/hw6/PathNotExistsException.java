package com.epam.concurrency.hw6;

public class PathNotExistsException extends RuntimeException {

    public PathNotExistsException(String message) {
        super(message);
    }
}
