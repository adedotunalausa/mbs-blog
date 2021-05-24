package com.adedotunalausa.week9taskadedotunalausa.exception;

import com.adedotunalausa.week9taskadedotunalausa.util.MethodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostCustomExceptionHandler {

    @ExceptionHandler(value = AppResourceNotFoundException.class)
    public ResponseEntity<String> postNotFoundException(AppResourceNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, exception), status);
    }
}
