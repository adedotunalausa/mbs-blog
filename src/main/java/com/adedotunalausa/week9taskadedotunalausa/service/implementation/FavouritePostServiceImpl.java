package com.adedotunalausa.week9taskadedotunalausa.service.implementation;

import com.adedotunalausa.week9taskadedotunalausa.model.FavouritePost;
import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.repository.FavouritePostRepository;
import com.adedotunalausa.week9taskadedotunalausa.service.FavouritePostService;
import com.adedotunalausa.week9taskadedotunalausa.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavouritePostServiceImpl implements FavouritePostService {

    private final FavouritePostRepository favouritePostRepository;
    private final PostService postService;

    @Override
    public ResponseEntity<String> addPostToFavourite(Long postId, Long userId) {
        Post post = postService.getPostById(postId);
        FavouritePost favouritePost = new FavouritePost(post, userId);
        favouritePostRepository.save(favouritePost);
        return new ResponseEntity<>("Post successfully added to favourites", HttpStatus.OK);
    }
}
