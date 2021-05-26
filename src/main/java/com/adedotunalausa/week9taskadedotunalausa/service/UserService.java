package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User getUserByUserId(Long userId);
    Page<User> getAllUsers(Pageable pageable);
    User getUserByUsername(String username);
}
