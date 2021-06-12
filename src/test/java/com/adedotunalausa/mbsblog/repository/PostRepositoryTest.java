package com.adedotunalausa.mbsblog.repository;

import com.adedotunalausa.mbsblog.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PostRepositoryTest {

    @Autowired
    private PostRepository underTestPostRepository;

    @Test
    void itShouldFindUserByUsername() {
        // given
        Post post = new Post("ade", 1632L,
                "test title", "test content");
        underTestPostRepository.save(post);

        // when
//        underTestPostRepository.f

        // then
    }
}