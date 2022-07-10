package com.evita.idvalidator.validators;

import com.evita.idvalidator.validators.exeption.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NationalIdValidator implements Validator {
    private final BirthDateValidator birthDateValidator = new BirthDateValidator();
    private final ControlDigitValidator controlDigitValidator = new ControlDigitValidator();
    private final DigitLengthValidator digitLengthValidator = new DigitLengthValidator();
    private final GenderValidator genderValidator = new GenderValidator();

    List<Validator> validators = List.of(
        genderValidator,
        birthDateValidator,
        digitLengthValidator,
        controlDigitValidator
    );

    public boolean validateNationalId(String nationalId) throws ValidationException {
        for (Validator validator : validators) {
            boolean isValid = validator.validateNationalId(nationalId);
            if (!isValid) {
                return false;
            }
        }
        return true;
    }
}
