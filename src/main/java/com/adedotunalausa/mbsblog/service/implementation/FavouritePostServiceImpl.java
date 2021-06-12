package com.adedotunalausa.mbsblog.service.implementation;

import com.adedotunalausa.mbsblog.model.FavouritePost;
import com.adedotunalausa.mbsblog.model.Post;
import com.adedotunalausa.mbsblog.repository.FavouritePostRepository;
import com.adedotunalausa.mbsblog.service.FavouritePostService;
import com.adedotunalausa.mbsblog.service.PostService;
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
