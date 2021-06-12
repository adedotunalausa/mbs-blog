package com.adedotunalausa.mbsblog.service.implementation;

import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.model.UserConnection;
import com.adedotunalausa.mbsblog.repository.UserConnectionRepository;
import com.adedotunalausa.mbsblog.service.UserConnectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserConnectionServiceImpl implements UserConnectionService {

    private final UserConnectionRepository userConnectionRepository;

    @Override
    public ResponseEntity<String> createConnection(User sender, User receiver) {
        if (userConnectionRepository.existsBySenderAndReceiver(sender, receiver)
                || userConnectionRepository.existsByReceiverAndSender(receiver, sender)) {
            return new ResponseEntity<>("Connection already established!", HttpStatus.BAD_REQUEST);
        }
        if (sender.getUserId().equals(receiver.getUserId())) {
            return new ResponseEntity<>("You can only connect with other users and not yourself!",
                    HttpStatus.BAD_REQUEST);
        }
        UserConnection newConnection = new UserConnection(sender, receiver);
        userConnectionRepository.save(newConnection);
        return new ResponseEntity<>("Connection created successfully", HttpStatus.OK);
    }

    @Override
    public List<UserConnection> getUserConnections(User user) {
        return userConnectionRepository.findAllBySenderEqualsOrReceiverEquals(user, user);
    }
}
