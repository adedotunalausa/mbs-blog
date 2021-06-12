package com.adedotunalausa.mbsblog.repository;

import com.adedotunalausa.mbsblog.model.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findCommentLikeByLikedByAndCommentId(String username, Long commentId);
}
