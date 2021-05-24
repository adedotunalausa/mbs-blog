package com.adedotunalausa.week9taskadedotunalausa.exception;

public class ApplicationException extends RuntimeException{

    private static final long serialVersionUID = -310198229906054487L;

    public ApplicationException(String message) {
        super(message);
    }
}
