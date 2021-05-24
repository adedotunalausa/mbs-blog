package com.adedotunalausa.week9taskadedotunalausa.service.implementation;

import com.adedotunalausa.week9taskadedotunalausa.exception.ApplicationException;
import com.adedotunalausa.week9taskadedotunalausa.exception.PostNotFoundException;
import com.adedotunalausa.week9taskadedotunalausa.model.Post;
import com.adedotunalausa.week9taskadedotunalausa.repository.PostRepository;
import com.adedotunalausa.week9taskadedotunalausa.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void createPost(Post newPost) {
        postRepository.save(newPost);
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> getPostsByUsername(String username, Pageable pageable) {
        return postRepository.findByUsername(username, pageable);
    }

    @Override
    public Post updatePost(Post currentPost) {
        Long currentPostId = currentPost.getPostId();
        if (currentPostId == null) {
            throw new ApplicationException("Post Id missing, Id is required for update");
        }
        return postRepository.findById(currentPostId).map(post -> {
            post.setPostId(currentPostId);
            post.setPostTitle(currentPost.getPostTitle());
            post.setPostContent(currentPost.getPostContent());
            return postRepository.save(post);
        }).orElseThrow(
                () -> new PostNotFoundException("Post not found")
        );
    }
}
