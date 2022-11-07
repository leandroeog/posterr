package com.strider.posterr.integration;

import com.strider.posterr.PosterrApplication;
import com.strider.posterr.application.infra.database.entity.PostEntity;
import com.strider.posterr.application.infra.database.repository.PostJpaRepository;
import com.strider.posterr.application.infra.fake.UserFake;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(classes = PosterrApplication.class)
public class UserControllerIT {

    private final MockMvc mockMvc;

    private final PostJpaRepository postJpaRepository;

    @Autowired
    public UserControllerIT(MockMvc mockMvc, PostJpaRepository postJpaRepository) {
        this.mockMvc = mockMvc;
        this.postJpaRepository = postJpaRepository;
    }

    @Test
    public void profileShouldShowUserInfo() throws Exception {

        generatePost();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/user/profile"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("user1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.joinDate", Matchers.is("November 1, 2022")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postsCount", Matchers.is(1)));

    }

    private void generatePost() {

        clearPosts();

        UUID userId = UserFake.getFirst();

        PostEntity entity = new PostEntity();

        entity.setId(UUID.randomUUID());
        entity.setUserId(userId);
        entity.setComment("my post comment");
        entity.setTimestamp(LocalDateTime.now());

        postJpaRepository.save(entity);

    }

    private void clearPosts() {
        postJpaRepository.deleteAll();
    }

}
