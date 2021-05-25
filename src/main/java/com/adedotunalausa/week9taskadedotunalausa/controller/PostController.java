package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.exception.ApplicationException;
import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.repository.UserRepository;
import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.PostService;
import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import com.adedotunalausa.week9taskadedotunalausa.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    private final JwtUtils jwtUtils;
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/posts")
    public Page<Post> allPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    @GetMapping("/my-posts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Post> userPosts(HttpServletRequest request, Pageable pageable) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        return postService.getPostsByUsername(username, pageable);
    }

    @PostMapping("/create-post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Post createPost(@Valid @RequestBody Post newPost, HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);

        if (currentUser != null) {
            Post post = new Post(username, newPost.getPostTitle(), newPost.getPostContent());
            postService.createPost(post);
            return post;
        }

        return null;
    }

    @PutMapping("/edit-post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Post editPost(@Valid @RequestBody Post currentPost, HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        Post savedPost = postService.getPostById(currentPost.getPostId());

        if (!username.equals(savedPost.getUsername())) {
            throw new ApplicationException("Operation failed! You can only edit posts posted by you");
        }

        return postService.updatePost(currentPost);
    }

    @GetMapping("/favourites-posts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String viewFavouritePosts() {
        return "Favourites.";
    }

}
