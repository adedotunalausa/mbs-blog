package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.model.UserConnection;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserConnectionService {
    ResponseEntity<String> createConnection(User sender, User receiver);
    List<UserConnection> getUserConnections(User user);
}
