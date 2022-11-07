package com.strider.posterr.application.usecase.input;

import com.strider.posterr.application.usecase.exception.InvalidHomeInputException;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class HomeInput {

    private boolean isPrivate;

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID userId;

    private int pageNumber;

    public HomeInput(
            boolean isPrivate,
            LocalDate startDate,
            LocalDate endDate,
            UUID userId,
            int pageNumber
    ) throws InvalidHomeInputException {

        if (startDate != null && endDate != null) {

            if (startDate.isAfter(endDate)) {
                throw new InvalidHomeInputException("Please inform a valid period");
            }

        }

        this.isPrivate = isPrivate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.pageNumber = pageNumber;

    }

}
