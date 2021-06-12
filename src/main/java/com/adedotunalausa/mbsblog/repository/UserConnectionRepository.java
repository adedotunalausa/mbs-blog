package com.adedotunalausa.mbsblog.repository;

import com.adedotunalausa.mbsblog.model.User;
import com.adedotunalausa.mbsblog.model.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {
    boolean existsByReceiverAndSender(User receiver, User sender);
    boolean existsBySenderAndReceiver(User sender, User receiver);
    List<UserConnection> findAllBySenderEqualsOrReceiverEquals(User sender, User receiver);
    void deleteAllBySenderEqualsOrReceiverEquals(User sender, User receiver);
}
