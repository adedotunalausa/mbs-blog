package com.adedotunalausa.week9taskadedotunalausa.repository;

import com.adedotunalausa.week9taskadedotunalausa.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
