package com.adedotunalausa.week9taskadedotunalausa.repository;

import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUsername(String username, Pageable pageable);
}
