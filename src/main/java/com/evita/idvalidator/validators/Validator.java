package com.evita.idvalidator.validators;

import com.evita.idvalidator.validators.exeption.ValidationException;

public interface Validator {
    boolean validateNationalId(String id) throws ValidationException;
}
