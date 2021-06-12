package com.adedotunalausa.mbsblog.service;

import org.springframework.http.ResponseEntity;

public interface FavouritePostService {
    ResponseEntity<String> addPostToFavourite(Long postId, Long userId);
}
