package com.prakash.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.entites.Comment;
import com.prakash.blog.entites.Post;
import com.prakash.blog.exception.ResourceNotFoundException;
import com.prakash.blog.mapper.CommentMapper;
import com.prakash.blog.repo.CommentRepo;
import com.prakash.blog.repo.PostRepo;

import jakarta.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	
	
	@Override
	@Transactional
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	   
	    Post post = this.postRepo.findById(postId)
	        .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

	   
	    Comment comment = CommentMapper.toEntity(commentDto);

	   
	    comment.setPost(post);

	 
	    Comment savedComment = this.commentRepo.save(comment);

	    return CommentMapper.toDto(savedComment);
	}


	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepo.delete(com);
		
	}

}
