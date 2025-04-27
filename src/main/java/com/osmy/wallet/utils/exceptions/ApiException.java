package com.osmy.wallet.utils.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ApiException extends Throwable {
    private String message;
    private String description;
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    private final int status = httpStatus.value();
    private final Map<String, Object> map = new HashMap<>();

    public ApiException(String message) {
        this(message, null);
    }

    public ApiException(String message, String description) {
        this.message = message;
        this.description = description;
        map.put("status", status);
        map.put("error", message);
        map.put("description", description);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        map.put("error", message);
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        map.put("description", description);
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
