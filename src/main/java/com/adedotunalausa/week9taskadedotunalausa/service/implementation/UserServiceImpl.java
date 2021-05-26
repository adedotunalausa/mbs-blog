package com.adedotunalausa.week9taskadedotunalausa.service.implementation;

import com.adedotunalausa.week9taskadedotunalausa.exception.AppResourceNotFoundException;
import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.repository.UserRepository;
import com.adedotunalausa.week9taskadedotunalausa.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new AppResourceNotFoundException("User not found!")
        );
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new AppResourceNotFoundException("User not found!")
        );
    }
}
