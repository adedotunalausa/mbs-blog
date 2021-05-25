package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.Comment;
import com.adedotunalausa.week9taskadedotunalausa.model.CommentLike;
import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.CommentLikeService;
import com.adedotunalausa.week9taskadedotunalausa.service.CommentService;
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
public class CommentLikeController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    @PostMapping("/like-comment")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> likeComment(@Valid @RequestBody CommentLike commentLike,
                                              HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);
        Comment currentComment = commentService.getCommentById(commentLike.getCommentId());

        if (currentUser != null && currentComment != null) {
            if (commentLikeService.getCommentLikeByUsernameAndCommentId(username,
                    commentLike.getCommentId()) == null) {
                Long currentCommentId = commentLike.getCommentId();
                CommentLike newCommentLike = new CommentLike(username, currentCommentId, true);
                return commentLikeService.createLike(newCommentLike);
            } else {
                return commentLikeService.updateLike(commentLike, username);
            }
        }

        return new ResponseEntity<>("User or comment is null", HttpStatus.OK);
    }
}
