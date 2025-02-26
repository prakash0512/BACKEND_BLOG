package com.prakash.blog.controller;

import com.prakash.blog.dto.ApiResponse;
import com.prakash.blog.dto.PostDto;
import com.prakash.blog.dto.PostResponse;
import com.prakash.blog.service.FileService;
import com.prakash.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private FileService fileService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePost() {
        PostDto postDto = new PostDto();
        postDto.setTitle("Test Post");
        postDto.setContent("Test Content");

        PostDto createdPost = new PostDto();
        createdPost.setTitle("Test Post");
        createdPost.setContent("Test Content");

        when(postService.createPost(any(PostDto.class), eq(1), eq(1))).thenReturn(createdPost);

        ResponseEntity<PostDto> response = postController.createPost(postDto, 1, 1);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Test Post", response.getBody().getTitle());
    }

    @Test
    void testUpdatePost() {
        PostDto postDto = new PostDto();
        postDto.setTitle("Updated Title");
        postDto.setContent("Updated Content");

        PostDto updatedPost = new PostDto();
        updatedPost.setTitle("Updated Title");
        updatedPost.setContent("Updated Content");

        when(postService.updatePost(any(PostDto.class), eq(1))).thenReturn(updatedPost);

        ResponseEntity<PostDto> response = postController.update(postDto, 1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Updated Title", response.getBody().getTitle());
    }

    @Test
    void testGetPostByUser() {
        PostDto postDto = new PostDto();
        postDto.setTitle("User Post");
        postDto.setContent("User Post Content");

        List<PostDto> posts = Arrays.asList(postDto);

        when(postService.getPostsByUser(eq(1))).thenReturn(posts);

        ResponseEntity<List<PostDto>> response = postController.getPostByUser(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("User Post", response.getBody().get(0).getTitle());
    }

  /*  @Test
    void testDeletePost() {
        ApiResponse response = new ApiResponse("Post Deleted Successfully", true);

        when(postService.deletePost(eq(1))).thenReturn(response);

        ResponseEntity<ApiResponse> result = postController.deletepostbyid(1);

        assertEquals(200, result.getStatusCodeValue());
        assertTrue(result.getBody().isSuccess());
        assertEquals("Post Deleted Successfully", result.getBody().getMessage());
    } */

    @Test
    void testUploadPostImage() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        PostDto postDto = new PostDto();
        postDto.setTitle("Post with Image");

        when(file.getOriginalFilename()).thenReturn("image.jpg");
        when(fileService.uploadImage(anyString(), eq(file))).thenReturn("image.jpg");
        when(postService.getPostById(1)).thenReturn(postDto);

        ResponseEntity<PostDto> response = postController.uploadPostImage(file, 1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Post with Image", response.getBody().getTitle());
        assertEquals("image.jpg", response.getBody().getImageName());
    }

    @Test
    void testDownloadImage() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(fileService.getResource(anyString(), eq("image.jpg"))).thenReturn(getClass().getResourceAsStream("/test.jpg"));

    postController.downloadImage("image.jpg", response);

        verify(response).setContentType("image/jpeg");
        verify(fileService).getResource(anyString(), eq("image.jpg"));
    }

}
