package com.strider.posterr.application.infra.database.repository;

import com.strider.posterr.application.domain.entity.Post;
import com.strider.posterr.application.infra.database.entity.PostEntity;
import com.strider.posterr.application.infra.database.specification.PostEntitySpecification;
import com.strider.posterr.application.repository.PostRepository;
import com.strider.posterr.application.repository.filter.HomeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostH2Repository implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Autowired
    public PostH2Repository(PostJpaRepository postJpaRepository) {
        this.postJpaRepository = postJpaRepository;
    }

    @Override
    public Post find(UUID id) {

        PostEntity entity = postJpaRepository.findById(id).orElseThrow();

        return new Post(
                entity.getId(),
                entity.getOtherPostId(),
                entity.getUserId(),
                entity.getComment(),
                entity.getTimestamp()
        );

    }

    @Override
    public Page<Post> findAll(HomeFilter filter, Pageable pageable) {

        PostEntitySpecification specification = new PostEntitySpecification(
                filter.getUserId(),
                filter.getStartDate(),
                filter.getEndDate()
        );

        return postJpaRepository.findAll(specification, pageable).map(it -> new Post(
                it.getId(),
                it.getOtherPostId(),
                it.getUserId(),
                it.getComment(),
                it.getTimestamp()
        ));

    }

    @Override
    public void save(Post post) {

        PostEntity entity = new PostEntity();

        entity.setId(post.getId());
        entity.setOtherPostId(post.getOtherPostId());
        entity.setUserId(post.getUserId());
        entity.setComment(post.getComment());
        entity.setTimestamp(post.getTimestamp());

        postJpaRepository.save(entity);

    }

    @Override
    public long countByUser(UUID userId) {
        return postJpaRepository.countByUserId(userId);
    }

}
