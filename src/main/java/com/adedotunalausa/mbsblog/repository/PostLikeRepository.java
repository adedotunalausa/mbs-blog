package com.adedotunalausa.mbsblog.repository;

import com.adedotunalausa.mbsblog.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByLikedByAndPostId(String username, Long postId);
}
