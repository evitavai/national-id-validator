package com.evita.idvalidator.validators.helpers;

import com.evita.idvalidator.validators.exeption.BirthDateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

@Component
public class BirthDateValidatorHelper {

    public static LocalDate parseBirthDate(String nationalId) throws BirthDateException {
        String strDate = nationalId.substring(1, 7);
        try {
            DateTimeFormatter dateFormat = new DateTimeFormatterBuilder()
                .appendValueReduced(ChronoField.YEAR, 2, 2, LocalDate.now().minusYears(100))
                .appendPattern("MMdd")
                .toFormatter();

            return LocalDate.parse(strDate, dateFormat.withResolverStyle(ResolverStyle.STRICT));
        } catch (Exception e) {
            throw new BirthDateException("Invalid birth date digits");
        }
    }
}
