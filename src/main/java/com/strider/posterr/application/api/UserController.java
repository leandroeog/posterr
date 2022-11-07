package com.strider.posterr.application.api;

import com.strider.posterr.application.infra.fake.UserFake;
import com.strider.posterr.application.usecase.ShowUserProfileUseCase;
import com.strider.posterr.application.usecase.input.UserProfileInput;
import com.strider.posterr.application.usecase.output.UserProfileOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final ShowUserProfileUseCase showUserProfileUseCase;

    @Autowired
    public UserController(ShowUserProfileUseCase showUserProfileUseCase) {
        this.showUserProfileUseCase = showUserProfileUseCase;
    }

    @GetMapping("profile")
    public ResponseEntity<?> showProfile() {

        UUID userId = UserFake.getFirst();

        UserProfileInput input = new UserProfileInput(userId);

        UserProfileOutput output = showUserProfileUseCase.execute(input);

        return ResponseEntity.ok(output);

    }

}
