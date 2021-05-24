package com.adedotunalausa.week9taskadedotunalausa.exception;

public class PostNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2533194229906054487L;

    public PostNotFoundException(String message) {
        super(message);
    }
}
