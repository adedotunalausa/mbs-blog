package com.adedotunalausa.mbsblog.service;

import com.adedotunalausa.mbsblog.model.CommentLike;
import org.springframework.http.ResponseEntity;

public interface CommentLikeService {
    ResponseEntity<String> createLike(CommentLike commentLike);
    ResponseEntity<String> updateLike(CommentLike commentLike, String username);
    CommentLike getCommentLikeByUsernameAndCommentId(String username, Long commentId);
}
