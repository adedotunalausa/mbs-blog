package com.adedotunalausa.mbsblog.service.implementation;

import com.adedotunalausa.mbsblog.exception.AppResourceNotFoundException;
import com.adedotunalausa.mbsblog.model.PostLike;
import com.adedotunalausa.mbsblog.repository.PostLikeRepository;
import com.adedotunalausa.mbsblog.service.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;

    @Override
    public ResponseEntity<String> createLike(PostLike postLike) {
        postLikeRepository.save(postLike);
        return new ResponseEntity<>("Post liked successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateLike(PostLike postLike, String username) {
        return postLikeRepository.findByLikedByAndPostId(username, postLike.getPostId()).map(currentPostLike -> {
            currentPostLike.setId(currentPostLike.getId());
            currentPostLike.setLiked(!currentPostLike.isLiked());
            postLikeRepository.save(currentPostLike);
            return new ResponseEntity<>("Like updated successfully", HttpStatus.OK);
        }).orElseThrow(
                () -> new AppResourceNotFoundException("Post Like not found!")
        );
    }

    @Override
    public PostLike getPostLikeByUsernameAndPostId(String username, Long postId) {
        if (postLikeRepository.findByLikedByAndPostId(username, postId).isPresent()) {
            return postLikeRepository.findByLikedByAndPostId(username, postId).get();
        } else {
            return null;
        }
    }
}
