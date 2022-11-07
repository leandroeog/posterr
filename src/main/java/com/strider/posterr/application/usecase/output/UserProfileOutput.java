package com.strider.posterr.application.usecase.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserProfileOutput {

    private String username;

    private String joinDate;

    long postsCount;

}
