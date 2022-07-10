package com.evita.idvalidator.validators;

import com.evita.idvalidator.validators.exeption.GenderException;
import org.springframework.stereotype.Component;

@Component
public class GenderValidator implements Validator {

    @Override
    public boolean validateNationalId(String nationalId) throws GenderException {
        if (nationalId.matches("^[3456].*")) {
            return true;
        } else {
            throw new GenderException("Invalid gender digit found in given national id");
        }
    }
}
