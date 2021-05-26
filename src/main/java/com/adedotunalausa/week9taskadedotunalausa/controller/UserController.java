package com.adedotunalausa.week9taskadedotunalausa.controller;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.model.UserConnection;
import com.adedotunalausa.week9taskadedotunalausa.security.jwt.JwtUtils;
import com.adedotunalausa.week9taskadedotunalausa.service.UserConnectionService;
import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import com.adedotunalausa.week9taskadedotunalausa.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserConnectionService userConnectionService;

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/my-connections")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> getMyConnections(HttpServletRequest request) {

        List<User> connections = new ArrayList<>();
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);
        List<UserConnection> userConnections = userConnectionService.getUserConnections(currentUser);

        for (UserConnection userConnection : userConnections) {
            if (userConnection.getReceiver() != currentUser) {
                connections.add(userConnection.getReceiver());
            }

            if (userConnection.getSender() != currentUser) {
                connections.add(userConnection.getSender());
            }
        }

        return connections;
    }


}
