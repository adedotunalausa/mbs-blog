package com.adedotunalausa.mbsblog.controller;

import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.model.UserConnection;
import com.adedotunalausa.mbsblog.security.jwt.JwtUtils;
import com.adedotunalausa.mbsblog.service.UserConnectionService;
import com.adedotunalausa.mbsblog.service.UserService;
import com.adedotunalausa.mbsblog.util.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/deactivate-account")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> deactivateAccount(HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);

        if (userService.setDeactivationDate(currentUser)) {
            return new ResponseEntity<>("Account deactivation in progress, account will be " +
                    "completed within 24 hours and can be canceled before then", HttpStatus.OK);
        }

        return new ResponseEntity<>("Account deactivation not successful", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cancel-deactivation")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> cancelAccountDeactivation(HttpServletRequest request) {
        String jwt = MethodUtils.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User currentUser = userService.getUserByUsername(username);

        if (userService.removeDeactivationDate(currentUser)) {
            return new ResponseEntity<>("Account deactivation canceled successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Account deactivation not canceled successful", HttpStatus.BAD_REQUEST);
    }
}
