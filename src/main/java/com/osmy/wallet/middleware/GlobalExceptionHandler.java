package com.osmy.wallet.middleware;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.osmy.wallet.utils.exceptions.NotFoundException;
import com.osmy.wallet.utils.exceptions.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedException ex, WebRequest wr) {
        log.error("Error ocurred: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMap(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(NotFoundException ex, WebRequest wr) {
        log.error("Error ocurred: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMap(), HttpStatus.NOT_FOUND);
    }
}