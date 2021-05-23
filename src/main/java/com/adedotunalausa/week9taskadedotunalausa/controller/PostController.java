package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    private final UserServiceImpl userServiceImpl;
    private final JwtUtils jwtUtils;

    @GetMapping("/my-posts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userPosts() {
        return "My Posts.";
    }

    @GetMapping("/create-post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String createPost() {

        return "Create post.";
    }

    @GetMapping("/favourites-posts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String viewFavouritePosts() {
        return "Favourites.";
    }


}
