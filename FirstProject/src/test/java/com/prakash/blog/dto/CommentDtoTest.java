package com.prakash.blog.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentDtoTest {

    @Test
    void testCommentDto() {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Test comment");
        
        assertEquals("Test comment", commentDto.getContent());
        
        commentDto.setContent("Updated comment");
        assertEquals("Updated comment", commentDto.getContent());
    }
}
