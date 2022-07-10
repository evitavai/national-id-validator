package com.evita.idvalidator.validators.exeption;

public class GenderException extends ValidationException {
    private static final String errorCode = "4";


    public GenderException(String errorMessage) {
        super(errorMessage, errorCode);
    }
}
