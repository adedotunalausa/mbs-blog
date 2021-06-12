package com.adedotunalausa.mbsblog.controller;

import com.adedotunalausa.mbsblog.exception.ApplicationException;
import com.adedotunalausa.mbsblog.model.Post;
import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.model.UserConnection;
import com.adedotunalausa.mbsblog.security.jwt.JwtUtils;
import com.adedotunalausa.mbsblog.service.PostService;
import com.adedotunalausa.mbsblog.service.UserConnectionService;
import com.adedotunalausa.mbsblog.service.UserService;
import com.adedotunalausa.mbsblog.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostController {

    private final JwtUtils jwtUtils;
    private final PostService postService;
    private final UserService userService;
    private final UserConnectionService userConnectionService;

    @GetMapping("/all-posts")
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

    @GetMapping("/my-connections-posts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Post> myConnectionPosts(HttpServletRequest request) {
        List<Post> posts = new ArrayList<>();
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);
        List<UserConnection> userConnections = userConnectionService.getUserConnections(currentUser);

        for(UserConnection userConnection: userConnections) {
            if (userConnection.getReceiver() != currentUser) {
                posts.addAll(userConnection.getReceiver().getPosts());
            }

            if (userConnection.getSender() != currentUser) {
                posts.addAll(userConnection.getSender().getPosts());
            }
        }

        return posts;
    }

    @PostMapping("/create-post")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Post createPost(@Valid @RequestBody Post newPost, HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);

        if (currentUser != null) {
            Post post = new Post(username, currentUser.getUserId(),
                    newPost.getPostTitle(), newPost.getPostContent());
            return postService.createPost(post);
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
            throw new ApplicationException("Operation failed! You can only edit posts created by you");
        }

        return postService.updatePost(currentPost);
    }

}
