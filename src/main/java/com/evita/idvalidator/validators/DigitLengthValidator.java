package com.evita.idvalidator.validators;

import com.evita.idvalidator.validators.exeption.DigitLengthException;
import org.springframework.stereotype.Component;

@Component
public class DigitLengthValidator implements Validator {

    @Override
    public boolean validateNationalId(String nationalId) throws DigitLengthException {
        if (nationalId.matches("\\d{11}")) {
            return true;
        } else {
            throw new DigitLengthException("Invalid digit length in given national id");
        }
    }
}
