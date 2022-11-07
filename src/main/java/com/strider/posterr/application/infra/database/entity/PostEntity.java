package com.strider.posterr.application.infra.database.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "id_other_post")
    @Type(type = "uuid-char")
    private UUID otherPostId;

    @Column(name = "id_user", nullable = false)
    @Type(type = "uuid-char")
    private UUID userId;

    @Column(length = 777)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime timestamp;

}
