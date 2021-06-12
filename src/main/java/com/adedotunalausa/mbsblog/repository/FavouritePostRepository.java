package com.adedotunalausa.mbsblog.repository;

import com.adedotunalausa.mbsblog.model.FavouritePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritePostRepository extends JpaRepository<FavouritePost, Long> {
}
