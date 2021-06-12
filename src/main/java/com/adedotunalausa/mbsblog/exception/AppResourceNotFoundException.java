package com.adedotunalausa.mbsblog.exception;

public class AppResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2533194229906054487L;

    public AppResourceNotFoundException(String message) {
        super(message);
    }
}
