package com.evita.idvalidator.validators;

import com.evita.idvalidator.validators.*;
import com.evita.idvalidator.validators.exeption.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.evita.idvalidator.validators.helpers.BirthDateValidatorHelper;
import com.evita.idvalidator.validators.helpers.GenderValidatorHelper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NationalIdValidatorTest {

    @Autowired
    NationalIdValidator nationalIdValidator;

    @Autowired
    DigitLengthValidator digitLengthValidator;

    @Autowired
    BirthDateValidator birthDateValidator;

    @Autowired
    ControlDigitValidator controlDigitValidator;

    @Autowired
    GenderValidator genderValidator;

    @Autowired
    BirthDateValidatorHelper birthDateValidatorHelper;

    @Autowired
    GenderValidatorHelper genderValidatorHelper;


    @Test
    void trueIfCorrectDigitLength() throws DigitLengthException {
        boolean is11Digits = digitLengthValidator.validateNationalId("49912300102");

        assertTrue(is11Digits);
    }

    @Test
    void throwsErrorIfIncorrectDigitLength() {
        DigitLengthException exception = assertThrows(DigitLengthException.class, () -> digitLengthValidator.validateNationalId("4991230002"), "Expected to be 11 digits, but wasn't");

        assertTrue(exception.getMessage().contains("Invalid digit length in given national id"));
    }

    @Test
    void trueIfCorrectBirthDate() throws BirthDateException {
        boolean isValidBirthDate = birthDateValidator.validateNationalId("49912300102");

        assertTrue(isValidBirthDate);
    }

    @Test
    void throwsErrorIfIncorrectBirthDate() {
        BirthDateException exception = assertThrows(BirthDateException.class, () -> birthDateValidator.validateNationalId("49900300102"), "Expected to be a valid birth date, but wasn't");

        assertTrue(exception.getMessage().contains("Invalid birth date parsed from national id"));
    }

    @Test
    void trueIfCorrectGender() throws GenderException {
        boolean isValidGender = genderValidator.validateNationalId("49912300102");

        assertTrue(isValidGender);
    }

    @Test
    void throwsErrorIfIncorrectGender() {
        GenderException exception = assertThrows(GenderException.class, () -> genderValidator.validateNationalId("79912300102"), "Expected to be valid gender, but wasn't");

        assertTrue(exception.getMessage().contains("Invalid gender digit found in given national id"));
    }

    @Test
    void trueIfCorrectControlDigit() throws ControlDigitException {
        boolean isValidControlDigit = controlDigitValidator.validateNationalId("49912300102");

        assertTrue(isValidControlDigit);
    }

    @Test
    void throwsErrorIfIncorrectControlDigit() {
        ControlDigitException exception = assertThrows(ControlDigitException.class, () -> controlDigitValidator.validateNationalId("49912300103"), "Expected to be a valid control digit, but wasn't");

        assertTrue(exception.getMessage().contains("Invalid control digit in given national id"));
    }

    @Test
    void parsesBirthDateFromNationalIdSuccessfully() throws BirthDateException {
        LocalDate birthDate = BirthDateValidatorHelper.parseBirthDate("49912300102");

        assertEquals(LocalDate.parse("1999-12-30"), birthDate);
    }

    @Test
    void parsesGenderFromNationalIdSuccessfully() throws GenderException {
        String gender = GenderValidatorHelper.parseGender("49912300103");

        assertEquals("FEMALE", gender);
    }

    @Test
    void validatesCorrectNationalIdSuccessfully() throws ValidationException {
        boolean isValid = nationalIdValidator.validateNationalId("49912300102");

        assertTrue(isValid);
    }

}