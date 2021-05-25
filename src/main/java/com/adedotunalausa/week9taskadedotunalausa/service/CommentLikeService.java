package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.CommentLike;
import org.springframework.http.ResponseEntity;

public interface CommentLikeService {
    ResponseEntity<String> createLike(CommentLike commentLike);
    ResponseEntity<String> updateLike(CommentLike commentLike, String username);
    CommentLike getCommentLikeByUsernameAndCommentId(String username, Long commentId);
}
