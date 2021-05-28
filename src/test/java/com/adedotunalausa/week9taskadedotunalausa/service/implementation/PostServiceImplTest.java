package com.adedotunalausa.week9taskadedotunalausa.service.implementation;

import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createPost() {
        Post newPost = new Post("ade", 1938L, "test title", "test content");

        given(postRepository.save(newPost)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Post savedPost = postService.createPost(newPost);

        assertNotNull(savedPost);

        verify(postRepository).save(any(Post.class));

    }

    @Test
    void getPostById() {
        Long postId = 1632L;
        Post newPost = new Post("ade", 1632L, "test title", "test content");

        System.out.println(newPost.getPostTitle());

        given(postRepository.findById(postId)).willReturn(java.util.Optional.of(newPost));

        Post expected = postService.getPostById(postId);

        assertNotNull(expected);
    }

    @Test
    void getAllPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("ade", 1632L, "test title", "test content"));
        posts.add(new Post("ade", 1632L, "another test title", "another test content"));

        given(postRepository.findAll()).willReturn(posts);

        List<Post> expected = postService.getAllPosts();

        assertEquals(expected, posts);
    }

    @Test
    void getPostsByUsername() {
        String username = "ade";
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("ade", 1632L, "test title", "test content"));
        posts.add(new Post("ade", 1632L, "another test title", "another test content"));

        given(postRepository.findByUsername(username)).willReturn(posts);

        List<Post> expected = postService.getPostsByUsername(username);

        assertEquals(expected, posts);
    }

    @Test
    void updatePost() {

    }
}