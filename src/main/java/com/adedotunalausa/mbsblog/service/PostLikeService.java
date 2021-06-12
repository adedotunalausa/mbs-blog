package com.adedotunalausa.mbsblog.service;

import com.adedotunalausa.mbsblog.model.PostLike;
import org.springframework.http.ResponseEntity;

public interface PostLikeService {
    ResponseEntity<String> createLike(PostLike postLike);
    ResponseEntity<String> updateLike(PostLike postLike, String username);
    PostLike getPostLikeByUsernameAndPostId(String username, Long postId);
}
