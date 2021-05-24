package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.Comment;
import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.CommentService;
import com.adedotunalausa.week9taskadedotunalausa.service.PostService;
import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import com.adedotunalausa.week9taskadedotunalausa.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/create-comment")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Comment createComment(@Valid @RequestBody Comment newComment, HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);
        Post currentPost = postService.getPostById(newComment.getPostId());

        if (currentUser != null && currentPost !=null) {
            Long currentPostId = newComment.getPostId();
            String commentBody = newComment.getCommentBody();
            Comment comment = new Comment(username, currentPostId, commentBody);
            commentService.createComment(comment);
            currentPost.getComments().add(comment);
            postService.createPost(currentPost);
            currentUser.getComments().add(comment);
            userService.saveOrUpdateUser(currentUser);
            return comment;
        }

        return null;
    }


}
