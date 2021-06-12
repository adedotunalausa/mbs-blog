package com.adedotunalausa.mbsblog.service.implementation;

import com.adedotunalausa.mbsblog.exception.AppResourceNotFoundException;
import com.adedotunalausa.mbsblog.exception.ApplicationException;
import com.adedotunalausa.mbsblog.model.Comment;
import com.adedotunalausa.mbsblog.repository.CommentRepository;
import com.adedotunalausa.mbsblog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment newComment) {
        return commentRepository.save(newComment);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new AppResourceNotFoundException("Comment not found!")
        );
    }

    @Override
    public Comment updateComment(Comment currentComment) {
        Long currentCommentId = currentComment.getCommentId();
        if (currentCommentId == null) {
            throw new ApplicationException("Comment Id is missing, Id is required for update");
        }

        return commentRepository.findById(currentCommentId).map(comment -> {
            comment.setCommentId(currentCommentId);
            comment.setCommentBody(currentComment.getCommentBody());
            return commentRepository.save(comment);
        }).orElseThrow(
                () -> new AppResourceNotFoundException("Post not found!")
        );
    }
}
