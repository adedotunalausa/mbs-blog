package com.adedotunalausa.mbsblog.controller;
import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.security.jwt.JwtUtils;
import com.adedotunalausa.mbsblog.service.FavouritePostService;
import com.adedotunalausa.mbsblog.service.UserService;
import com.adedotunalausa.mbsblog.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FavouritePostController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final FavouritePostService favouritePostService;

    @PostMapping("/add-to-favourites/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> addToFavourites(@PathVariable Long postId,
                                                  HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);
        return favouritePostService.addPostToFavourite(
                postId, currentUser.getUserId()
        );
    }
}
