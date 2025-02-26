package com.prakash.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.prakash.blog.dto.PostDto;
import com.prakash.blog.dto.PostResponse;
import com.prakash.blog.entites.Category;
import com.prakash.blog.entites.Post;
import com.prakash.blog.entites.User;
import com.prakash.blog.exception.ResourceNotFoundException;
import com.prakash.blog.mapper.PostMapper;
import com.prakash.blog.repo.CategoryRepo;
import com.prakash.blog.repo.CommentRepo;
import com.prakash.blog.repo.PostRepo;
import com.prakash.blog.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService{
    
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
	   
	    User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

	    Category category = this.categoryRepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

	    
	    Post post = PostMapper.postDtoToPost(postDto);

	    post.setAddedDate(new Date());

	    if (post.getImageName() == null || post.getImageName().isEmpty()) {
	        post.setImageName("default.png");  
	    }

	    post.setUser(user);
	    post.setCategory(category);

	   
	    Post savedPost = this.postRepo.save(post);

	   return PostMapper.postToPostDto(savedPost);
	}


	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		  Post post = this.postRepo.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));
		  if (postDto.getCategory() == null) {
		        throw new IllegalArgumentException("Category is required for the post.");
		    }

	        Category category = this.categoryRepo.findById(postDto.getCategory().getCategoryId()).get();

	        post.setTitle(postDto.getTitle());
	        post.setContent(postDto.getContent());
	        post.setImageName(postDto.getImageName());
	        post.setCategory(category);


	        Post updatedPost = this.postRepo.save(post);
	        return PostMapper.postToPostDto(updatedPost);
		
	}

	
	
	@Override
	@Transactional
	public void deletePost(Integer postId) {
	    try {
	       
	        Post post = postRepo.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

	        
	        System.out.println("delete is working fine: " + post.getContent());

	       
	        User user = post.getUser();
	        if (user != null) {
	           
	            user.getPosts().remove(post);
	          
	            userRepo.save(user);
	        }

	      
	        commentRepo.deleteByPost(post);

	       
	        postRepo.delete(post);
	        postRepo.flush(); 

	       
	        System.out.println("deleted");

	       
	        if (postRepo.existsById(postId)) {
	            System.out.println("Still exists: " + postId);
	        } else {
	            System.out.println("Post does not exist: " + postId);
	        }
	    } catch (Exception e) {
	       
	        System.err.println("Error deleting post: " + e.getMessage());
	        throw e;
	    }
	}


	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return PostMapper.postToPostDto(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		 Category cat = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
	        List<Post> posts = this.postRepo.findByCategory(cat);

	        List<PostDto> postDtos = PostMapper.postListToPostDtoList(posts);

	        return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = PostMapper.postListToPostDtoList(posts);
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		  List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
		  List<PostDto> postDtos = PostMapper.postListToPostDtoList(posts);
			return postDtos;
	}


	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = PostMapper.postListToPostDtoList(allPosts);

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());

        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
	}

}
