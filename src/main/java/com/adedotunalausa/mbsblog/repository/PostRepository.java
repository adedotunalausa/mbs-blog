package com.adedotunalausa.mbsblog.repository;

import com.adedotunalausa.mbsblog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUsername(String username, Pageable pageable);

    List<Post> findByUsername(String username); // for testing

}
