package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.model.PostLike;
import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.PostLikeService;
import com.adedotunalausa.week9taskadedotunalausa.service.PostService;
import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import com.adedotunalausa.week9taskadedotunalausa.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostLikeController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PostService postService;
    private final PostLikeService postLikeService;

    @PostMapping("/like-post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> likePost(@Valid @RequestBody PostLike postLike, HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);
        Post currentPost = postService.getPostById(postLike.getPostId());

        if (currentUser != null && currentPost !=null) {
            if (postLikeService.getPostLikeByUsernameAndPostId(username, postLike.getPostId()) == null) {
                Long currentPostId = postLike.getPostId();
                PostLike newPostLike = new PostLike(username, currentPostId, true);
                return postLikeService.createLike(newPostLike);
            } else {
                return postLikeService.updateLike(postLike, username);
            }
        }

        return new ResponseEntity<>("User or post is null", HttpStatus.BAD_REQUEST);
    }
}
