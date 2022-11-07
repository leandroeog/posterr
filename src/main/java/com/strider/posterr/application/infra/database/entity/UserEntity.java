package com.strider.posterr.application.infra.database.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_info") //changing name due to H2 user table
public class UserEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(length = 14, nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDate joinDate;

}
