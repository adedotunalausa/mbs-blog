package com.adedotunalausa.week9taskadedotunalausa.service.implementation;

import com.adedotunalausa.week9taskadedotunalausa.exception.AppResourceNotFoundException;
import com.adedotunalausa.week9taskadedotunalausa.model.CommentLike;
import com.adedotunalausa.week9taskadedotunalausa.repository.CommentLikeRepository;
import com.adedotunalausa.week9taskadedotunalausa.service.CommentLikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    @Override
    public ResponseEntity<String> createLike(CommentLike commentLike) {
        commentLikeRepository.save(commentLike);
        return new ResponseEntity<>("Comment liked successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateLike(CommentLike commentLike, String username) {
        return commentLikeRepository.findCommentLikeByLikedByAndCommentId(username, commentLike.getCommentId())
                .map(currentCommentLike -> {
                    currentCommentLike.setId(currentCommentLike.getId());
                    currentCommentLike.setLiked(!currentCommentLike.isLiked());
                    commentLikeRepository.save(currentCommentLike);
                    return new ResponseEntity<>("Like updated successfully", HttpStatus.OK);
                }).orElseThrow(
                        () -> new AppResourceNotFoundException("Comment like not found!")
                );
    }

    @Override
    public CommentLike getCommentLikeByUsernameAndCommentId(String username, Long commentId) {
        if (commentLikeRepository.findCommentLikeByLikedByAndCommentId(username, commentId).isPresent()) {
            return commentLikeRepository.findCommentLikeByLikedByAndCommentId(username, commentId).get();
        } else {
            return null;
        }
    }
}
