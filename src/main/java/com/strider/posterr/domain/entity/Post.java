package com.strider.posterr.domain.entity;

import com.strider.posterr.domain.exception.InvalidPostException;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Post {

    private UUID id;

    private UUID otherPostId;

    private UUID userId;

    private String comment;

    private LocalDateTime timestamp = LocalDateTime.now();

    //post
    public Post(UUID userId, String comment) throws InvalidPostException {

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.comment = comment;

        validate(this);

    }

    //repost
    private Post(UUID otherPostId, UUID userId) throws InvalidPostException {

        this.id = UUID.randomUUID();
        this.otherPostId = otherPostId;
        this.userId = userId;

        validate(this);

    }

    //quote
    private Post(UUID otherPostId, UUID userId, String comment) throws InvalidPostException {

        this.id = UUID.randomUUID();
        this.otherPostId = otherPostId;
        this.userId = userId;
        this.comment = comment;

        validate(this);

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

    private void validate(Post post) throws InvalidPostException {

        if (post.comment != null && post.comment.length() > 777) {
            throw new InvalidPostException("Sorry, you can create a post with 777 characters at max");
        }

    }

    private boolean isRepost() {
        return otherPostId != null && comment == null;
    }

    private boolean isQuote() {
        return otherPostId != null && comment != null;
    }

}
