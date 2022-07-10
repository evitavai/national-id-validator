package com.evita.idvalidator.validators.exeption;

public class BirthDateException extends ValidationException {

    private static final String errorCode = "1";

    public BirthDateException(String errorMessage) {
        super(errorMessage, errorCode);
    }
}
