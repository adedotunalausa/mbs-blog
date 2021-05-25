package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.PostLike;
import org.springframework.http.ResponseEntity;

public interface PostLikeService {
    ResponseEntity<String> createLike(PostLike postLike);
    ResponseEntity<String> updateLike(PostLike postLike, String username);
    PostLike getPostLikeByUsernameAndPostId(String username, Long postId);
}
