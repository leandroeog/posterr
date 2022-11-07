package com.strider.posterr.application.usecase;

import com.strider.posterr.application.domain.entity.User;
import com.strider.posterr.application.repository.PostRepository;
import com.strider.posterr.application.repository.UserRepository;
import com.strider.posterr.application.usecase.input.UserProfileInput;
import com.strider.posterr.application.usecase.output.UserProfileOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ShowUserProfileUseCase {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public ShowUserProfileUseCase(
            @Qualifier("userH2Repository") UserRepository userRepository,
            @Qualifier("postH2Repository") PostRepository postRepository
    ) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public UserProfileOutput execute(UserProfileInput input) {

        User user = userRepository.find(input.getUserId());

        String username = user.getUsername();
        String joinDate = DateTimeFormatter.ofPattern("MMMM d, yyyy").format(user.getJoinDate());
        long postsCount = postRepository.countByUser(input.getUserId());

        return new UserProfileOutput(username, joinDate, postsCount);

    }

}
