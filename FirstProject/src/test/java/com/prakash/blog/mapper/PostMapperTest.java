package com.prakash.blog.mapper;

import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.dto.PostDto;
import com.prakash.blog.dto.UserDto;
import com.prakash.blog.entites.Category;
import com.prakash.blog.entites.Comment;
import com.prakash.blog.entites.Post;
import com.prakash.blog.entites.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class PostMapperTest {

    @Test
    void testPostToPostDto() {
       
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("Post Title");
        post.setContent("Post content here.");
        post.setImageName("image.jpg");
       post.setAddedDate("2025-02-26");

        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryTitle("Tech");
        category.setCategoryDescription("Tech related posts");

        User user = new User();
        user.setId(1);
        user.setName("Prakash");

        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setContent("Great post!");

        Set<Comment> comments = new HashSet<>();
        comments.add(comment1);

        post.setCategory(category);
        post.setUser(user);
        post.setComments(comments);

        // Convert Post to PostDto
        PostDto postDto = PostMapper.postToPostDto(post);

        assertNotNull(postDto);
        assertEquals(post.getPostId(), postDto.getPostId());
        assertEquals(post.getTitle(), postDto.getTitle());
        assertEquals(post.getContent(), postDto.getContent());
        assertEquals(post.getImageName(), postDto.getImageName());
        assertEquals(post.getAddedDate(), postDto.getAddedDate());
        assertNotNull(postDto.getCategory());
        assertNotNull(postDto.getUser());
        assertNotNull(postDto.getComments());
    }

    @Test
    void testPostDtoToPost() {
        // Create a PostDto
        PostDto postDto = new PostDto();
        postDto.setPostId(1);
        postDto.setTitle("Post Title");
        postDto.setContent("Post content here.");
        postDto.setImageName("image.jpg");
       postDto.setAddedDate("2025-02-26");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryTitle("Tech");
        categoryDto.setCategoryDescription("Tech related posts");

        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("Prakash");

        CommentDto commentDto = new CommentDto();
        commentDto.setId(1);
        commentDto.setContent("Great post!");

        Set<CommentDto> comments = new HashSet<>();
        comments.add(commentDto);

        postDto.setCategory(categoryDto);
        postDto.setUser(userDto);
        postDto.setComments(comments);

        
        Post post = PostMapper.postDtoToPost(postDto);

        assertNotNull(post);
        assertEquals(postDto.getPostId(), post.getPostId());
        assertEquals(postDto.getTitle(), post.getTitle());
        assertEquals(postDto.getContent(), post.getContent());
        assertEquals(postDto.getImageName(), post.getImageName());
        assertEquals(postDto.getAddedDate(), post.getAddedDate());
        assertNotNull(post.getCategory());
        assertNotNull(post.getUser());
        assertNotNull(post.getComments());
    }
}
