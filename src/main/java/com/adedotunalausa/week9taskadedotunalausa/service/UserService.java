package com.adedotunalausa.week9taskadedotunalausa.service;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User getUserByUserId(Long userId);
    Page<User> getAllUsers(Pageable pageable);
    User getUserByUsername(String username);
    boolean setDeactivationDate(User currentUser);
    boolean removeDeactivationDate(User currentUser);
    void deactivateUsers();
}
