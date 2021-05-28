package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.Comment;

public interface CommentService {
    Comment createComment(Comment newComment);
    Comment getCommentById(Long commentId);
    Comment updateComment(Comment currentComment);
}
