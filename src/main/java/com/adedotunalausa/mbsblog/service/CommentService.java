package com.adedotunalausa.mbsblog.service;

import com.adedotunalausa.mbsblog.model.Comment;

public interface CommentService {
    Comment createComment(Comment newComment);
    Comment getCommentById(Long commentId);
    Comment updateComment(Comment currentComment);
}
