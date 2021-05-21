package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.User;

public interface UserService {
    User createUser(User user);
    User getUser(String email, String password);
}
