package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.repository.UserRepository;
import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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
    private final UserRepository userRepository;

    @GetMapping("/posts")
    public Page<Post> allPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    @GetMapping("/my-posts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Post> userPosts(HttpServletRequest request, Pageable pageable) {
        String jwt = parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        return postService.getPostsByUsername(username, pageable);
    }

    @PostMapping("/create-post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Post createPost(@Valid @RequestBody Post newPost, HttpServletRequest request) {
        String jwt = parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userRepository.findByUsername(username).get();

        if (userRepository.findByUsername(username).isPresent()) {
            Post post = new Post(username, newPost.getPostTitle(), newPost.getPostContent());
            postService.createPost(post);
            currentUser.getPosts().add(post);
            userRepository.save(currentUser);
            return post;
        }

        return null;
    }

    @PutMapping("/edit-post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Post editPost(@Valid @RequestBody Post currentPost) {
        return postService.updatePost(currentPost);
    }

    @GetMapping("/favourites-posts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String viewFavouritePosts() {
        return "Favourites.";
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

}
