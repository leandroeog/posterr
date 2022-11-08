package com.strider.posterr.application.api;

import com.strider.posterr.application.infra.fake.UserFake;
import com.strider.posterr.application.usecase.ShowUserFeedUseCase;
import com.strider.posterr.application.usecase.ShowUserProfileUseCase;
import com.strider.posterr.application.usecase.input.UserFeedInput;
import com.strider.posterr.application.usecase.input.UserProfileInput;
import com.strider.posterr.application.usecase.output.UserFeedOutput;
import com.strider.posterr.application.usecase.output.UserProfileOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final ShowUserProfileUseCase showUserProfileUseCase;

    private final ShowUserFeedUseCase showUserFeedUseCase;

    @Autowired
    public UserController(ShowUserProfileUseCase showUserProfileUseCase, ShowUserFeedUseCase showUserFeedUseCase) {
        this.showUserProfileUseCase = showUserProfileUseCase;
        this.showUserFeedUseCase = showUserFeedUseCase;
    }

    @GetMapping("profile")
    public ResponseEntity<?> showProfile() {

        UUID userId = UserFake.getFirst();

        UserProfileInput input = new UserProfileInput(userId);

        UserProfileOutput output = showUserProfileUseCase.execute(input);

        return ResponseEntity.ok(output);

    }

    @GetMapping("feed")
    public ResponseEntity<?> showFeed(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {

        UUID userId = UserFake.getFirst();

        UserFeedInput input = new UserFeedInput(userId, pageNumber);

        UserFeedOutput output = showUserFeedUseCase.execute(input);

        return ResponseEntity.ok(output);

    }

}
