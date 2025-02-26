package com.prakash.blog.mapper;

import com.prakash.blog.entites.Comment;
import com.prakash.blog.dto.CommentDto;

public class CommentMapper {

    
    public static CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        
        return commentDto;
    }

    
    public static Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        
        return comment;
    }
}
