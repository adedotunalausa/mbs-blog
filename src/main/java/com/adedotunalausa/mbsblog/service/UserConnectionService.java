package com.adedotunalausa.mbsblog.service;

import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.model.UserConnection;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserConnectionService {
    ResponseEntity<String> createConnection(User sender, User receiver);
    List<UserConnection> getUserConnections(User user);
}
