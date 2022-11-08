package com.strider.posterr.application.domain.entity;

import com.strider.posterr.application.domain.exception.InvalidUserException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class User {

    public static final int USERNAME_MAX_LENGTH = 14;
    public static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]+$";

    private UUID id = UUID.randomUUID();

    private String username;

    private LocalDate joinDate = LocalDate.now();

    public User(String username) throws InvalidUserException {

        validate(username);

        this.username = username;

    }

    private static void validate(String username) throws InvalidUserException {

        if (username == null || username.isEmpty()) {
            throw new InvalidUserException("Please, inform something as your username");
        }

        if (username.length() > USERNAME_MAX_LENGTH) {
            throw new InvalidUserException("Sorry, you can create your username with " + USERNAME_MAX_LENGTH + " characters at max");
        }

        if (!Pattern.compile(ALPHANUMERIC_REGEX).matcher(username).matches()) {
            throw new InvalidUserException("Sorry, you can't create your username with non alphanumeric characters");
        }

    }

}
