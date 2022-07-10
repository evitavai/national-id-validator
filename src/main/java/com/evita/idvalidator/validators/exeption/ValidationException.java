package com.evita.idvalidator.validators.exeption;

import lombok.Getter;

@Getter
public class ValidationException extends Exception {
    private final String errorCode;

    public ValidationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
