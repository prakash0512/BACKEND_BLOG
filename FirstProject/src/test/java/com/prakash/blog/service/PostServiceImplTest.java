package com.prakash.blog.service;

import com.prakash.blog.dto.PostDto;
import com.prakash.blog.dto.PostResponse;
import com.prakash.blog.entites.Category;
import com.prakash.blog.entites.Post;
import com.prakash.blog.entites.User;
import com.prakash.blog.exception.ResourceNotFoundException;
import com.prakash.blog.mapper.PostMapper;
import com.prakash.blog.repo.CategoryRepo;
import com.prakash.blog.repo.PostRepo;
import com.prakash.blog.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private PostRepo postRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private PostServiceImpl postService;

    private PostDto postDto;
    private Post post;
    private User user;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        postDto = new PostDto();
        postDto.setTitle("Test Post");
        postDto.setContent("Test Content");

        post = new Post();
        post.setPostId(1);
        post.setTitle("Test Post");
        post.setContent("Test Content");
        post.setAddedDate(new Date());

        user = new User();
        user.setId(1);
        user.setName("Prakash");

    
    }

    @Test
    void testCreatePost() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        when(postRepo.save(any(Post.class))).thenReturn(post);

        PostDto createdPost = postService.createPost(postDto, 1, 1);

        assertNotNull(createdPost);
        assertEquals("Test Post", createdPost.getTitle());
    }

    @Test
    void testUpdatePost() {
        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        when(postRepo.save(any(Post.class))).thenReturn(post);

        PostDto updatedPost = postService.updatePost(postDto, 1);

        assertNotNull(updatedPost);
        assertEquals("Test Post", updatedPost.getTitle());
    }

    @Test
    void testDeletePost() {
        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        doNothing().when(postRepo).delete(post);

        postService.deletePost(1);

        verify(postRepo, times(1)).delete(post);
    }

    @Test
    void testGetPostById() {
        when(postRepo.findById(1)).thenReturn(Optional.of(post));

        PostDto foundPost = postService.getPostById(1);

        assertNotNull(foundPost);
        assertEquals("Test Post", foundPost.getTitle());
    }

    @Test
    void testGetAllPost() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Post> posts = Arrays.asList(post);
        Page<Post> page = new PageImpl<>(posts);

        when(postRepo.findAll(pageable)).thenReturn(page);

       PostResponse response = postService.getAllPost(0, 5, "title", "asc");

        assertNotNull(response);
        assertEquals(1, response.getContent().size());
        assertEquals("Test Post", response.getContent().get(0).getTitle());
    }
}
