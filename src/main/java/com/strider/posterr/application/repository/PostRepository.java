package com.strider.posterr.application.repository;

import com.strider.posterr.application.domain.entity.Post;
import com.strider.posterr.application.repository.filter.HomeFilter;
import com.strider.posterr.application.repository.filter.UserFeedFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface PostRepository {

    Post find(UUID id);

    Page<Post> findAll(HomeFilter filter, Pageable pageable);

    Page<Post> findAll(UserFeedFilter filter, Pageable pageable);

    void save(Post post);

    long countByUser(UUID userId);

}
