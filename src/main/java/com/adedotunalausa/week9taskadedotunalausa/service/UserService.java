package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.User;

public interface UserService {
    void saveOrUpdateUser(User user);
    User getUserByUsername(String username);
}
