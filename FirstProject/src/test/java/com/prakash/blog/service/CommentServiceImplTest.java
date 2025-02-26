package com.prakash.blog.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.entites.Comment;
import com.prakash.blog.entites.Post;
import com.prakash.blog.exception.ResourceNotFoundException;
import com.prakash.blog.repo.CommentRepo;
import com.prakash.blog.repo.PostRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CommentServiceImplTest {

    @Mock
    private CommentRepo commentRepo;

    @Mock
    private PostRepo postRepo;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Post post;
    private CommentDto commentDto;
    private Comment comment;

    @BeforeEach
    void setUp() {
        post = new Post();
        post.setPostId(1);
        post.setTitle("Test Post");

        commentDto = new CommentDto();
        commentDto.setContent("This is a test comment");
        commentDto.setId(1);

        comment = new Comment();
        comment.setContent("This is a test comment");
        comment.setId(1);
        comment.setPost(post);
    }

    @Test
    void createComment_ShouldReturnCommentDto_WhenPostExists() {
        when(postRepo.findById(anyInt())).thenReturn(Optional.of(post));
        when(commentRepo.save(any(Comment.class))).thenReturn(comment);

        CommentDto result = commentService.createComment(commentDto, 1);

        assertNotNull(result);
        assertEquals(commentDto.getContent(), result.getContent());
        assertEquals(commentDto.getId(), result.getId());
        verify(postRepo, times(1)).findById(1);
        verify(commentRepo, times(1)).save(any(Comment.class));
    }

    @Test
    void createComment_ShouldThrowResourceNotFoundException_WhenPostDoesNotExist() {
        when(postRepo.findById(anyInt())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            commentService.createComment(commentDto, 1);
        });

        assertEquals("Post not found with id: 1", exception.getMessage());
        verify(postRepo, times(1)).findById(1);
        verify(commentRepo, never()).save(any(Comment.class));
    }

    @Test
    void deleteComment_ShouldDeleteComment_WhenCommentExists() {
        when(commentRepo.findById(anyInt())).thenReturn(Optional.of(comment));
        commentService.deleteComment(1);
        verify(commentRepo, times(1)).delete(comment);
    }

    @Test
    void deleteComment_ShouldThrowResourceNotFoundException_WhenCommentDoesNotExist() {
        when(commentRepo.findById(anyInt())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            commentService.deleteComment(1);
        });

        assertEquals("Comment not found with id: 1", exception.getMessage());
        verify(commentRepo, times(1)).findById(1);
        verify(commentRepo, never()).delete(any(Comment.class));
    }
}