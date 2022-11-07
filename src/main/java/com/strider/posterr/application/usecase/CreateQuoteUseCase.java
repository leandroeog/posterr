package com.strider.posterr.application.usecase;

import com.strider.posterr.application.domain.entity.Post;
import com.strider.posterr.application.domain.exception.InvalidPostException;
import com.strider.posterr.application.repository.PostRepository;
import com.strider.posterr.application.usecase.input.QuoteInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CreateQuoteUseCase {

    private final PostRepository postRepository;

    @Autowired
    public CreateQuoteUseCase(@Qualifier("postH2Repository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(QuoteInput input) throws InvalidPostException {

        Post post = postRepository.find(input.getOtherPostId());

        Post quote = post.quote(input.getUserId(), input.getComment());

        postRepository.save(quote);

    }

}
