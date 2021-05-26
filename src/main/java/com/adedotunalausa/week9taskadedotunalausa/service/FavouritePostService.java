package com.adedotunalausa.week9taskadedotunalausa.service;

import org.springframework.http.ResponseEntity;

public interface FavouritePostService {
    ResponseEntity<String> addPostToFavourite(Long postId, Long userId);
}
