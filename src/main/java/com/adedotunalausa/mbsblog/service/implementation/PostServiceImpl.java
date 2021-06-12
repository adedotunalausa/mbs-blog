package com.adedotunalausa.mbsblog.service.implementation;

import com.adedotunalausa.mbsblog.exception.ApplicationException;
import com.adedotunalausa.mbsblog.exception.AppResourceNotFoundException;
import com.adedotunalausa.mbsblog.model.Post;
import com.adedotunalausa.mbsblog.repository.PostRepository;
import com.adedotunalausa.mbsblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new AppResourceNotFoundException("Post not found!")
        );
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> getPostsByUsername(String username, Pageable pageable) {
        return postRepository.findByUsername(username, pageable);
    }

    @Override
    public List<Post> getPostsByUsername(String username) {
        return postRepository.findByUsername(username);
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
                () -> new AppResourceNotFoundException("Post not found!")
        );
    }
}
