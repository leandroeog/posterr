package com.strider.posterr.integration;

import com.jayway.jsonpath.JsonPath;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(classes = PosterrApplication.class)
public class HomeControllerIT {

    private final MockMvc mockMvc;

    private final PostJpaRepository postJpaRepository;

    @Autowired
    public HomeControllerIT(MockMvc mockMvc, PostJpaRepository postJpaRepository) {
        this.mockMvc = mockMvc;
        this.postJpaRepository = postJpaRepository;
    }

    @Test
    public void homeShouldShowLast10() throws Exception {

        generatePosts();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/home"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts.content", Matchers.hasSize(10)))
                .andDo(result -> {

                    String response = result.getResponse().getContentAsString();

                    String firstPostTime = JsonPath.parse(response).read("$.posts.content[0].timestamp");
                    String lastPostTime = JsonPath.parse(response).read("$.posts.content[9].timestamp");

                    assertThat(firstPostTime, Matchers.greaterThan(lastPostTime));

                });

    }

    @Test
    public void homeShouldShowOnlyPrivate() throws Exception {

        generatePosts();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/home").param("isPrivate", "true"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts.content[*].userId").value(Matchers.hasItem(UserFake.getFirst().toString())));

    }

    @Test
    public void homeShouldFilterByStartDate() throws Exception {

        generatePosts();

        String startDate = LocalDate.now().plusDays(1).toString();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/home").param("startDate", startDate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts.content", Matchers.hasSize(0)));

    }

    @Test
    public void homeShouldFilterByEndDate() throws Exception {

        generatePosts();

        String endDate = LocalDate.now().minusDays(1).toString();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/home").param("endDate", endDate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts.content", Matchers.hasSize(0)));

    }

    @Test
    public void homeShouldFilterByPeriod() throws Exception {

        generatePosts();

        String startDate = LocalDate.now().minusDays(1).toString();
        String endDate = LocalDate.now().plusDays(1).toString();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/home").param("startDate", startDate).param("endDate", endDate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.posts.content", Matchers.hasSize(10)));

    }

    public void generatePosts() {

        clearPosts();

        int quantity = 15;

        while (quantity > 0) {

            UUID userId = quantity > 5 ? UserFake.getFirst() : UserFake.getLast();

            PostEntity entity = new PostEntity();

            entity.setId(UUID.randomUUID());
            entity.setUserId(userId);
            entity.setComment("my post comment " + quantity);
            entity.setTimestamp(LocalDateTime.now());

            postJpaRepository.save(entity);

            quantity--;

        }

    }

    private void clearPosts() {
        postJpaRepository.deleteAll();
    }

}
