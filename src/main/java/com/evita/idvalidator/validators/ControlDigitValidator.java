package com.evita.idvalidator.validators;

import com.evita.idvalidator.validators.exeption.ControlDigitException;
import org.springframework.stereotype.Component;

@Component
public class ControlDigitValidator implements Validator {


    @Override
    public boolean validateNationalId(String nationalId) throws ControlDigitException {
        int sum = 0;
        char[] charArrayID = nationalId.toCharArray();
        int controlDigit = Character.getNumericValue(charArrayID[charArrayID.length - 1]);

        for (int i = 0; i < charArrayID.length - 1; i++) {
            if ((i + 1) % 9 == 0) {
                sum += (Character.getNumericValue(charArrayID[i]) * 9);
            } else {
                sum += (Character.getNumericValue(charArrayID[i]) * ((i + 1) % 9));
            }
        }
        int result = sum % 11;

        if (result < 10 && result == controlDigit) {
            return true;
        } else {
            return revalidateControlDigit(charArrayID, controlDigit, sum);
        }
    }

    private boolean revalidateControlDigit(char[] charArrayID, int controlDigit, int sum) throws ControlDigitException {
        for (int i = 0; i < charArrayID.length - 1; i++) {
            if ((i + 3) % 9 == 0) {
                sum += (Character.getNumericValue(charArrayID[i]) * 9);
            } else {
                sum += (Character.getNumericValue(charArrayID[i]) * ((i + 3) % 9));
            }
        }
        int result = sum % 11;

        if ((result < 10 && result == controlDigit) || charArrayID[charArrayID.length - 1] == 0) {
            return true;
        } else {
            throw new ControlDigitException("Invalid control digit in given national id");
        }
    }
}

