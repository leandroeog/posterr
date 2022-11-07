package com.strider.posterr.application.repository;

import com.strider.posterr.application.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserRepository {

    User find(UUID id);

}
