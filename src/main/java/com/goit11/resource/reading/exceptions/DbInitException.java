package com.goit11.resource.reading.exceptions;

public class DbInitException extends RuntimeException {
    public DbInitException() {
    }

    public DbInitException(String message) {
        super(message);
    }

    public DbInitException(String message, Throwable cause) {
        super(message, cause);
    }
}