package com.adedotunalausa.week9taskadedotunalausa.exception;

import com.adedotunalausa.week9taskadedotunalausa.util.MethodUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PostExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NotNull Exception ex, Object body,
                                                             @NotNull HttpHeaders headers,
                                                             @NotNull HttpStatus status,
                                                             @NotNull WebRequest request) {

        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, ex), status);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {

        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, ex), status);
    }

}
