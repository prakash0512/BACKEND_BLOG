package com.prakash.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prakash.blog.config.AppConstants;
import com.prakash.blog.dto.ApiResponse;
import com.prakash.blog.dto.PostDto;
import com.prakash.blog.dto.PostResponse;
import com.prakash.blog.service.FileService;
import com.prakash.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/post")
public class PostController {

	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/create/user/{uid}/category/{cid}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("uid") Integer userId,
			@PathVariable("cid") Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> update(@RequestBody PostDto postDto, @PathVariable("id") Integer pid){
			PostDto post=this.postService.updatePost(postDto, pid);
			return new ResponseEntity<PostDto>(post, HttpStatus.OK);
			
	}
	
	@GetMapping("/getbyuserid/{id}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("id") Integer uid){
		List<PostDto> post= this.postService.getPostsByUser(uid);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}
	

	@GetMapping("/getbycategory/{id}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Integer cid){
		List<PostDto> post= this.postService.getPostsByCategory(cid);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}
	
	@GetMapping("/getpostbyid/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ApiResponse deletepostbyid(@PathVariable("id") Integer postid){
		this.postService.deletePost(postid);
		
		return new ApiResponse("Post Deleted SucessFully",true);
	}
	
	@GetMapping("/getallpost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	
	
	
	
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image_name") MultipartFile image,
	                                               @PathVariable("postId") Integer postId) throws IOException {

	    if (image == null || image.isEmpty()) {
	        System.out.println("No image file received.");
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Return bad request if no image is sent
	    }

	    System.out.println("Received file: " + image.getOriginalFilename());
	    System.out.println("File type: " + image.getContentType());
	    System.out.println("File size: " + image.getSize() + " bytes");

	  
	    PostDto postDto = this.postService.getPostById(postId);

	  
	    String fileName = this.fileService.uploadImage(path, image);
	    postDto.setImageName(fileName);

	    
	    PostDto updatedPost = this.postService.updatePost(postDto, postId);
	    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}

	 @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadImage(
	            @PathVariable("imageName") String imageName,
	            HttpServletResponse response
	    ) throws IOException {

	        InputStream resource = this.fileService.getResource(path, imageName);
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(resource,response.getOutputStream())   ;

	    }

	}
	
	

