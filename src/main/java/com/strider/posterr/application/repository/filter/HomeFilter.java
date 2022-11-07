package com.strider.posterr.application.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class HomeFilter {

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID userId;

}
