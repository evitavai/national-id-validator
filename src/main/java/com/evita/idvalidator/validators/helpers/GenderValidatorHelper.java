package com.evita.idvalidator.validators.helpers;

import com.evita.idvalidator.validators.exeption.GenderException;
import org.springframework.stereotype.Component;

@Component
public class GenderValidatorHelper {
    public static String parseGender(String nationalId) throws GenderException {
        int firstChar = Character.getNumericValue(nationalId.charAt(0));
        if (firstChar == 4 || firstChar == 6) {
            return Gender.FEMALE.toString();
        } else if (firstChar == 3 || firstChar == 5) {
            return Gender.MALE.toString();
        } else {
            throw new GenderException("Could not parse gender from given national id");
        }
    }

    enum Gender {
        FEMALE, MALE,
    }
}
