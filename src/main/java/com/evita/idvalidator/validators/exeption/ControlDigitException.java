package com.evita.idvalidator.validators.exeption;

public class ControlDigitException extends ValidationException {

    private static final String errorCode = "2";

    public ControlDigitException(String errorMessage) {
        super(errorMessage, errorCode);
    }
}
