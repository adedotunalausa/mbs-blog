package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    void createPost(Post newPost);
    Page<Post> getAllPosts(Pageable pageable);
    Page<Post> getPostsByUsername(String username, Pageable pageable);
    Post updatePost(Post currentPost);
}