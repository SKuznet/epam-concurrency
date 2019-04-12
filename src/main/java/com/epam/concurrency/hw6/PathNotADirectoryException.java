package com.epam.concurrency.hw6;

public class PathNotADirectoryException extends RuntimeException {

    public PathNotADirectoryException(String message) {
        super(message);
    }
}
