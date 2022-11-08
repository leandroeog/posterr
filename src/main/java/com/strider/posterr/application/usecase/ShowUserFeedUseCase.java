package com.strider.posterr.application.usecase;

import com.strider.posterr.application.domain.entity.Post;
import com.strider.posterr.application.repository.PostRepository;
import com.strider.posterr.application.repository.filter.UserFeedFilter;
import com.strider.posterr.application.usecase.input.UserFeedInput;
import com.strider.posterr.application.usecase.output.UserFeedOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ShowUserFeedUseCase {

    private final PostRepository postRepository;

    private static final int USER_FEED_SIZE = 5;

    private static final String USER_FEED_ORDER_BY = "timestamp";

    public ShowUserFeedUseCase(@Qualifier("postH2Repository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public UserFeedOutput execute(UserFeedInput input) {

        Pageable pageable = PageRequest.of(
                input.getPageNumber(),
                USER_FEED_SIZE,
                Sort.by(Sort.Order.desc(USER_FEED_ORDER_BY))
        );

        UserFeedFilter filter = new UserFeedFilter(input.getUserId());

        Page<Post> posts = postRepository.findAll(filter, pageable);

        return new UserFeedOutput(posts);

    }

}
