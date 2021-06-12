package com.adedotunalausa.mbsblog.controller;

import com.adedotunalausa.mbsblog.exception.ApplicationException;
import com.adedotunalausa.mbsblog.model.Comment;
import com.adedotunalausa.mbsblog.model.Post;
import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.service.CommentService;
import com.adedotunalausa.mbsblog.service.PostService;
import com.adedotunalausa.mbsblog.service.UserService;
import com.adedotunalausa.mbsblog.util.MethodUtils;
import com.adedotunalausa.mbsblog.security.jwt.JwtUtils;
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
            Comment comment = new Comment(username, currentUser.getUserId(), currentPostId, commentBody);
            return commentService.createComment(comment);
        }

        return null;
    }

    @PutMapping("/edit-comment")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Comment editComment(@Valid @RequestBody Comment currentComment, HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        Comment savedComment = commentService.getCommentById(currentComment.getCommentId());

        if (!username.equals(savedComment.getCommentBy())) {
            throw new ApplicationException("Operation failed! You can only edit comments created by you");
        }

        return commentService.updateComment(currentComment);
    }
}
