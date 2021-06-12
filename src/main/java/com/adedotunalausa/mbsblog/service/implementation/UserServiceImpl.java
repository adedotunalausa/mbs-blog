package com.adedotunalausa.mbsblog.service.implementation;

import com.adedotunalausa.mbsblog.exception.AppResourceNotFoundException;
import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.repository.UserRepository;
import com.adedotunalausa.mbsblog.service.UserService;
//import com.adedotunalausa.week9taskadedotunalausa.repository.UserConnectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private final UserConnectionRepository userConnectionRepository;

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

    @Override
    public boolean setDeactivationDate(User currentUser) {
        boolean response = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 24);
            String deactivationDate = dateFormat.format(calendar.getTime());
            currentUser.setDeactivationDate(deactivationDate);
            userRepository.save(currentUser);
            response = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean removeDeactivationDate(User currentUser) {
        boolean response = false;
        try {
            if (!currentUser.getDeactivationDate().isEmpty()) {
                currentUser.setDeactivationDate(null);
                userRepository.save(currentUser);
                response = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void deactivateUsers() {
        List<User> users = userRepository.findAllByDeactivationDateNotNullAndUsernameNotNull();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        users.forEach(user -> {
            String today = dateFormat.format(date);
            String deactivationDate = user.getDeactivationDate();
            int deactivationIndication = today.compareTo(deactivationDate);
            if (deactivationIndication >= 0) {
                user.setUsername(null);
                user.setIsDeactivated(true);
                userRepository.save(user);
//                userConnectionRepository.deleteAllBySenderEqualsOrReceiverEquals(user, user);
            }
        });
    }

}
