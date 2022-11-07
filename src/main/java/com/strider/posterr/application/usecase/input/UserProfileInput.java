package com.strider.posterr.application.usecase.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserProfileInput {

    private UUID userId;

}
