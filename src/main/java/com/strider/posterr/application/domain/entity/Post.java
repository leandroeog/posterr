package com.strider.posterr.application.domain.entity;

import com.strider.posterr.application.domain.exception.InvalidPostException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Post {

    public static final int COMMENT_MAX_LENGTH = 777;

    private UUID id = UUID.randomUUID();

    private UUID otherPostId;

    private UUID userId;

    private String comment;

    private LocalDateTime timestamp = LocalDateTime.now();

    //post
    public Post(UUID userId, String comment) throws InvalidPostException {

        validate(comment);

        this.userId = userId;
        this.comment = comment;

    }

    //repost
    private Post(UUID otherPostId, UUID userId) {

        this.otherPostId = otherPostId;
        this.userId = userId;

    }

    //quote
    private Post(UUID otherPostId, UUID userId, String comment) throws InvalidPostException {

        validate(comment);

        this.otherPostId = otherPostId;
        this.userId = userId;
        this.comment = comment;

    }

    public Post repost(UUID userId) throws InvalidPostException {

        if (this.isRepost()) {
            throw new InvalidPostException("Sorry, you can't repost a repost");
        }

        if (this.getUserId().equals(userId)) {
            throw new InvalidPostException("Sorry, you can't repost your own post");
        }

        return new Post(this.id, userId);

    }

    public Post quote(UUID userId, String comment) throws InvalidPostException {
        return new Post(this.id, userId, comment);

    }

    private boolean isRepost() {
        return otherPostId != null && comment == null;
    }

    private static void validate(String comment) throws InvalidPostException {

        if (comment == null || comment.isEmpty()) {
            throw new InvalidPostException("Please, inform something in your comment");
        }

        if (comment.length() > COMMENT_MAX_LENGTH) {
            throw new InvalidPostException("Sorry, you can create a post with " + COMMENT_MAX_LENGTH + " characters at max");
        }

    }

}
