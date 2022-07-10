package com.evita.idvalidator.validators.exeption;

public class DigitLengthException extends ValidationException {
    private static final String errorCode = "3";


    public DigitLengthException(String errorMessage) {
        super(errorMessage, errorCode);
    }
}
