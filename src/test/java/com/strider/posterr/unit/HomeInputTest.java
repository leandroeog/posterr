package com.strider.posterr.unit;

import com.strider.posterr.application.usecase.exception.InvalidHomeInputException;
import com.strider.posterr.application.usecase.input.HomeInput;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomeInputTest {

    @Test
    public void startDateCantBeAfterEndDate() {

        LocalDate startDate = LocalDate.now().plusDays(1);

        LocalDate endDate = LocalDate.now();

        assertThrows(InvalidHomeInputException.class, () -> new HomeInput(false, startDate, endDate, null, 0));

    }

}
