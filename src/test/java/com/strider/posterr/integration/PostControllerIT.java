package com.strider.posterr.integration;

import com.strider.posterr.PosterrApplication;
import com.strider.posterr.application.infra.database.repository.PostJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(classes = PosterrApplication.class)
public class PostControllerIT {

    private final MockMvc mockMvc;

    private final PostJpaRepository postJpaRepository;

    @Autowired
    public PostControllerIT(MockMvc mockMvc, PostJpaRepository postJpaRepository) {
        this.mockMvc = mockMvc;
        this.postJpaRepository = postJpaRepository;
    }

    @Test
    public void postsShouldBeCreated() throws Exception {

        int postSizeBeforeCreate = postJpaRepository.findAll().size();

        String postComment = "my post comment";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/post").content(postComment))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        int postSizeAfterCreate = postJpaRepository.findAll().size();

        assertEquals(postSizeAfterCreate, postSizeBeforeCreate + 1);

    }


    @Test
    public void repostsShouldBeCreated() throws Exception {

        postsShouldBeCreated();

        int postSizeBeforeCreate = postJpaRepository.findAll().size();

        UUID createdPostId = postJpaRepository.findAll().get(0).getId();

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/post/" + createdPostId + "/repost"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        int postSizeAfterCreate = postJpaRepository.findAll().size();

        assertEquals(postSizeAfterCreate, postSizeBeforeCreate + 1);

    }

    @Test
    public void quotesShouldBeCreated() throws Exception {

        postsShouldBeCreated();

        int postSizeBeforeCreate = postJpaRepository.findAll().size();

        UUID createdPostId = postJpaRepository.findAll().get(0).getId();

        String quoteComment = "my quote comment";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/post/" + createdPostId + "/quote").content(quoteComment))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        int postSizeAfterCreate = postJpaRepository.findAll().size();

        assertEquals(postSizeAfterCreate, postSizeBeforeCreate + 1);

    }

}
