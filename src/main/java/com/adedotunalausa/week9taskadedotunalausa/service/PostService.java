package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Post createPost(Post newPost);
    Post getPostById(Long postId);
    Page<Post> getAllPosts(Pageable pageable);
    List<Post> getAllPosts(); // for testing
    Page<Post> getPostsByUsername(String username, Pageable pageable);
    List<Post> getPostsByUsername(String username); // for testing
    Post updatePost(Post currentPost);
}
