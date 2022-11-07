package com.strider.posterr.application.infra.fake;

import java.util.Random;
import java.util.UUID;

public class UserFake {

    private static final UUID[] users = {
            UUID.fromString("e39d461b-b486-47e6-8cf3-b1b876ce611c"),
            UUID.fromString("f6cbb446-201d-428a-91e1-da0b9c5cd9fc"),
            UUID.fromString("f5621cdd-a0c3-4efb-8a16-a566a947988e"),
            UUID.fromString("7b3cc1d2-53fd-4d3e-b3eb-0cf6b4b9cdae")
    };

    public static UUID getRandom() {

        int position = new Random().nextInt(users.length);

        return users[position];

    }

    public static UUID getDefault() {
        return users[0];
    }

}
