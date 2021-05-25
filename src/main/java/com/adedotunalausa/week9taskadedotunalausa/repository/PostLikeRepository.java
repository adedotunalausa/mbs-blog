package com.adedotunalausa.week9taskadedotunalausa.repository;

import com.adedotunalausa.week9taskadedotunalausa.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByLikedByAndPostId(String username, Long postId);
}
