package com.strider.posterr.application.usecase.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserFeedInput {

    private UUID userId;

    private int pageNumber;

}
