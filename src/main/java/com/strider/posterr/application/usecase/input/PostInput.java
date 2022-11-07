package com.strider.posterr.application.usecase.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class PostInput {

    UUID userId;

    String comment;

}
