package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.UserConnectionService;
import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import com.adedotunalausa.week9taskadedotunalausa.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserConnectionController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserConnectionService userConnectionService;

    @PostMapping("/create-connection/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> connect(@PathVariable Long userId, HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User sender = userService.getUserByUsername(username);
        User receiver = userService.getUserByUserId(userId);
        return userConnectionService.createConnection(sender, receiver);
    }
}
