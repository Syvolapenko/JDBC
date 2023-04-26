package com.goit11.resource.reading.exceptions;

public class ResourceReadException extends RuntimeException {
    public ResourceReadException() {
    }

    public ResourceReadException(String message) {
        super(message);
    }

    public ResourceReadException(String message, Throwable cause) {
        super(message, cause);
    }
}