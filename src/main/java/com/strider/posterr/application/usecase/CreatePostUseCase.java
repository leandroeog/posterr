package com.strider.posterr.application.usecase;

import com.strider.posterr.application.domain.entity.Post;
import com.strider.posterr.application.domain.exception.InvalidPostException;
import com.strider.posterr.application.repository.PostRepository;
import com.strider.posterr.application.usecase.input.PostInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CreatePostUseCase {

    private final PostRepository postRepository;

    @Autowired
    public CreatePostUseCase(@Qualifier("postH2Repository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(PostInput input) throws InvalidPostException {

        Post post = new Post(input.getUserId(), input.getComment());

        postRepository.save(post);

    }

}
