package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.service.implementation.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostServiceImpl postServiceMock;

    @Test
    void shouldGetAllPosts() throws Exception {
        // Given
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("ade", 1632L, "test title", "test content"));
        posts.add(new Post("ade", 1632L, "another test title", "another test content"));

        // when
        when(postServiceMock.getAllPosts()).thenReturn(posts);

        // then
        mockMvc.perform(get("/api/all-posts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void userPosts() {
    }

    @Test
    void myConnectionPosts() {
    }

    @Test
    void createPost() {
    }

    @Test
    void editPost() {
    }
}