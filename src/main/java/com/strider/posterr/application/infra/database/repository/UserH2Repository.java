package com.strider.posterr.application.infra.database.repository;

import com.strider.posterr.application.domain.entity.User;
import com.strider.posterr.application.infra.database.entity.UserEntity;
import com.strider.posterr.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserH2Repository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserH2Repository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User find(UUID id) {

        UserEntity entity = userJpaRepository.findById(id).orElseThrow();

        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getJoinDate()
        );

    }

}
