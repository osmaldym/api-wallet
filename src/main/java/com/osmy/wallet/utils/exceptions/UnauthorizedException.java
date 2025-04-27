package com.osmy.wallet.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message, null);
    }

    public UnauthorizedException(String message, String description) {
        super(message, description);
    }
}
