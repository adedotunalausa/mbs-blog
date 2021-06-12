package com.adedotunalausa.mbsblog.service.implementation;

import com.adedotunalausa.mbsblog.model.Comment;
import com.adedotunalausa.mbsblog.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void createComment() {
        Comment newComment = new Comment("Isaiah", 2311L, 2323L, "Test Comment");

        given(commentRepository.save(newComment)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Comment savedComment = commentService.createComment(newComment);

        assertNotNull(savedComment);

        verify(commentRepository).save(any(Comment.class));

    }

    @Test
    void getCommentById() {
        Long commentId = 234L;
        Comment newComment = new Comment("Isaiah", 2311L, 2323L, "Test Comment");

        given(commentRepository.findById(commentId)).willReturn(java.util.Optional.of(newComment));

        Comment expected = commentService.getCommentById(commentId);

        assertNotNull(expected);

    }

}