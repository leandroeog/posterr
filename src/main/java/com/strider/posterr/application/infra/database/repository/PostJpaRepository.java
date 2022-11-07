package com.strider.posterr.application.infra.database.repository;

import com.strider.posterr.application.infra.database.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, UUID>, JpaSpecificationExecutor<PostEntity> {

    long countByUserId(UUID userId);

}
