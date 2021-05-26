package com.adedotunalausa.week9taskadedotunalausa.repository;

import com.adedotunalausa.week9taskadedotunalausa.model.User;
import com.adedotunalausa.week9taskadedotunalausa.model.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {
    boolean existsByReceiverAndSender(User receiver, User sender);
    boolean existsBySenderAndReceiver(User sender, User receiver);
}
