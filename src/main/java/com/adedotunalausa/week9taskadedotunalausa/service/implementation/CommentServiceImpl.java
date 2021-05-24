package com.adedotunalausa.week9taskadedotunalausa.service.implementation;

import com.adedotunalausa.week9taskadedotunalausa.model.Comment;
import com.adedotunalausa.week9taskadedotunalausa.repository.CommentRepository;
import com.adedotunalausa.week9taskadedotunalausa.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(Comment newComment) {
        commentRepository.save(newComment);
    }
}
