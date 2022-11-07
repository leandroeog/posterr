package com.strider.posterr.application.repository;

import com.strider.posterr.application.domain.entity.Post;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface PostRepository {

    Post find(UUID id);

    void save(Post post);

}
