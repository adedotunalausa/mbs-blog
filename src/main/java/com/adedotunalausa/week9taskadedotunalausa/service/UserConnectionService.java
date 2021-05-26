package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import org.springframework.http.ResponseEntity;

public interface UserConnectionService {
    ResponseEntity<String> createConnection(User sender, User receiver);
}
