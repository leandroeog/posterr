package com.strider.posterr.unit;

import com.strider.posterr.domain.entity.Post;
import com.strider.posterr.domain.exception.InvalidPostException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostTest {

    @Test
    public void postsCantHaveMoreThan777Characters() {

        UUID userId = UUID.randomUUID();

        assertThrows(InvalidPostException.class, () -> new Post(userId, "7".repeat(778)));

    }

    @Test
    public void postsCanBeReposted() throws InvalidPostException {

        Post post = createPost();

        UUID repostUserId = UUID.randomUUID();
        Post repost = post.repost(repostUserId);

        assertEquals(repostUserId, repost.getUserId());
        assertEquals(post.getId(), repost.getOtherPostId());

    }

    @Test
    public void postsCanBeQuoted() throws InvalidPostException {

        Post post = createPost();

        UUID quoteUserId = UUID.randomUUID();
        String quoteComment = "my quote comment";
        Post quote = post.quote(quoteUserId, quoteComment);

        assertEquals(quoteUserId, quote.getUserId());
        assertEquals(quoteComment, quote.getComment());
        assertEquals(post.getId(), quote.getOtherPostId());

    }

    @Test
    public void postsCantBeRepostedByTheOwner() throws InvalidPostException {

        Post post = createPost();

        assertThrows(InvalidPostException.class, () -> post.repost(post.getUserId()));

    }

    @Test
    public void quotesCanBeReposted() throws InvalidPostException {

        Post quote = createQuote();

        UUID repostUserId = UUID.randomUUID();
        Post repost = quote.repost(repostUserId);

        assertEquals(repostUserId, repost.getUserId());
        assertEquals(quote.getId(), repost.getOtherPostId());

    }

    @Test
    public void repostsCantBeReposted() throws InvalidPostException {

        Post repost = createRepost();

        UUID repostUserId = UUID.randomUUID();

        assertThrows(InvalidPostException.class, () -> repost.repost(repostUserId));

    }

    @Test
    public void repostsCanBeQuoted() throws InvalidPostException {

        Post repost = createRepost();

        UUID quoteUserId = UUID.randomUUID();
        String quoteComment = "my quote comment";
        Post quote = repost.quote(quoteUserId, quoteComment);

        assertEquals(quoteUserId, quote.getUserId());
        assertEquals(quoteComment, quote.getComment());
        assertEquals(repost.getId(), quote.getOtherPostId());

    }

    private Post createPost() throws InvalidPostException {
        return new Post(UUID.randomUUID(), "my post comment");
    }

    private Post createRepost() throws InvalidPostException {
        return new Post(UUID.randomUUID(), "my post comment").repost(UUID.randomUUID());
    }

    private Post createQuote() throws InvalidPostException {
        return new Post(UUID.randomUUID(), "my post comment").quote(UUID.randomUUID(), "my quote comment");
    }

}
