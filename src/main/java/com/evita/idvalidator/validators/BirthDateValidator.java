package com.evita.idvalidator.validators;

import org.springframework.stereotype.Component;
import com.evita.idvalidator.validators.exeption.BirthDateException;

import static com.evita.idvalidator.validators.helpers.BirthDateValidatorHelper.parseBirthDate;

@Component
public class BirthDateValidator implements Validator {


    @Override
    public boolean validateNationalId(String nationalId) throws BirthDateException {
        String strDate = nationalId.substring(1, 7);
        if (strDate.matches("000000")) {
            return true;
        } else {
            try {
                parseBirthDate(nationalId);
            } catch (Exception e) {
                throw new BirthDateException("Invalid birth date parsed from national id");
            }
        }
        return true;
    }
}
