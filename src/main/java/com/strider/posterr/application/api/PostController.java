package com.strider.posterr.application.api;

import com.strider.posterr.application.domain.exception.InvalidPostException;
import com.strider.posterr.application.usecase.CreatePostUseCase;
import com.strider.posterr.application.usecase.CreateQuoteUseCase;
import com.strider.posterr.application.usecase.CreateRepostUseCase;
import com.strider.posterr.application.usecase.input.PostInput;
import com.strider.posterr.application.usecase.input.QuoteInput;
import com.strider.posterr.application.usecase.input.RepostInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/post")
public class PostController {

    private final CreatePostUseCase createPostUseCase;

    private final CreateRepostUseCase createRepostUseCase;

    private final CreateQuoteUseCase createQuoteUseCase;

    @Autowired
    public PostController(
            CreatePostUseCase createPostUseCase,
            CreateRepostUseCase createRepostUseCase,
            CreateQuoteUseCase createQuoteUseCase
    ) {
        this.createPostUseCase = createPostUseCase;
        this.createRepostUseCase = createRepostUseCase;
        this.createQuoteUseCase = createQuoteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String comment) {

        try {

            UUID userId = UUID.randomUUID();

            PostInput input = new PostInput(userId, comment);

            createPostUseCase.execute(input);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (InvalidPostException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("{id}/repost")
    public ResponseEntity<?> repost(@PathVariable UUID id) {

        try {

            UUID userId = UUID.randomUUID();

            RepostInput input = new RepostInput(id, userId);

            createRepostUseCase.execute(input);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (InvalidPostException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("{id}/quote")
    public ResponseEntity<?> quote(@PathVariable UUID id, @RequestBody String comment) {

        try {

            UUID userId = UUID.randomUUID();

            QuoteInput input = new QuoteInput(id, userId, comment);

            createQuoteUseCase.execute(input);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (InvalidPostException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
