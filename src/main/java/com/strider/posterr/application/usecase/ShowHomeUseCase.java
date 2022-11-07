package com.strider.posterr.application.usecase;

import com.strider.posterr.application.domain.entity.Post;
import com.strider.posterr.application.repository.PostRepository;
import com.strider.posterr.application.repository.filter.HomeFilter;
import com.strider.posterr.application.usecase.input.HomeInput;
import com.strider.posterr.application.usecase.output.HomeOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ShowHomeUseCase {

    private final PostRepository postRepository;

    private static final int POSTS_HOME_SIZE = 10;

    private static final String POSTS_HOME_ORDER_BY = "timestamp";

    public ShowHomeUseCase(@Qualifier("postH2Repository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public HomeOutput execute(HomeInput input) {

        Pageable pageable = PageRequest.of(
                input.getPageNumber(),
                POSTS_HOME_SIZE,
                Sort.by(Sort.Order.desc(POSTS_HOME_ORDER_BY))
        );

        HomeFilter filter = new HomeFilter(
                input.getStartDate(),
                input.getEndDate(),
                input.isPrivate() ? input.getUserId() : null
        );

        Page<Post> posts = postRepository.findAll(filter, pageable);

        return new HomeOutput(posts);

    }

}
