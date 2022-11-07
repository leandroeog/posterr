package com.strider.posterr.unit;

import com.strider.posterr.application.domain.entity.User;
import com.strider.posterr.application.domain.exception.InvalidUserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    @Test
    public void usernameCanOnlyContainAlphanumericCharacters() {
        assertThrows(InvalidUserException.class, () -> new User("leandroeog#"));
    }

    @Test
    public void usernameCantHaveMoreThan14Characters() {
        assertThrows(InvalidUserException.class, () -> new User("leandroeogleandroeog"));
    }

}
