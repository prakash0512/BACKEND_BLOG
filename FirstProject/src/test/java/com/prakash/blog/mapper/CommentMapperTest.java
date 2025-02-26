package com.prakash.blog.mapper;

import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.entites.Comment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommentMapperTest {

    @Test
    void testToDto() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setContent("Great post!");

        CommentDto commentDto = CommentMapper.toDto(comment);

        assertNotNull(commentDto);
        assertEquals(comment.getId(), commentDto.getId());
        assertEquals(comment.getContent(), commentDto.getContent());
    }

    @Test
    void testToDtoNull() {
        CommentDto commentDto = CommentMapper.toDto(null);
        assertNull(commentDto);
    }

    @Test
    void testToEntity() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1);
        commentDto.setContent("Great post!");

        Comment comment = CommentMapper.toEntity(commentDto);

        assertNotNull(comment);
        assertEquals(commentDto.getId(), comment.getId());
        assertEquals(commentDto.getContent(), comment.getContent());
    }

    @Test
    void testToEntityNull() {
        Comment comment = CommentMapper.toEntity(null);
        assertNull(comment);
    }
}
