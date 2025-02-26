package com.prakash.blog.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prakash.blog.dto.ApiResponse;
import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.service.CommentService;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private CommentDto commentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentDto = new CommentDto();
        commentDto.setContent("Test comment");
    }

    @Test
    void createComment() {
        when(commentService.createComment(commentDto, 1)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = commentController.createComment(commentDto, 1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(commentDto, response.getBody());
    }

    @Test
    void deleteComment() {
        doNothing().when(commentService).deleteComment(1);

        ResponseEntity<ApiResponse> response = commentController.deleteComment(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Comment deleted successfully !!", response.getBody().getMessage());
    }
} 
