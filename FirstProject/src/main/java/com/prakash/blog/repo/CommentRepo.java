package com.prakash.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prakash.blog.entites.Comment;
import com.prakash.blog.entites.Post;



public interface CommentRepo  extends JpaRepository<Comment	, Integer> {

	 void deleteByPost(Post post);
}

