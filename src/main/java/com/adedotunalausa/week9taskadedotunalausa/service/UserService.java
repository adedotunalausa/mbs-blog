package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.User;

public interface UserService {
    String signUpUser(User user);
    int enableUser(String email);
//    User getUser(String email, String password);
}
