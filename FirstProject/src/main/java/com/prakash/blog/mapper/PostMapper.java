package com.prakash.blog.mapper;

import com.prakash.blog.dto.PostDto;
import com.prakash.blog.entites.Category;
import com.prakash.blog.entites.Comment;
import com.prakash.blog.entites.Post;
import com.prakash.blog.entites.User;
import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public static PostDto postToPostDto(Post post) {
        PostDto postDto = new PostDto();

        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageName(post.getImageName());
        postDto.setAddedDate(post.getAddedDate());

        if (post.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(post.getCategory().getCategoryId());
            categoryDto.setCategoryTitle(post.getCategory().getCategoryTitle());
            categoryDto.setCategoryDescription(post.getCategory().getCategoryDescription());
            postDto.setCategory(categoryDto);
        } else {
            postDto.setCategory(null);
        }

        if (post.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(post.getUser().getId());
            userDto.setName(post.getUser().getName());
            userDto.setEmail(post.getUser().getEmail());
            userDto.setPassword(post.getUser().getPassword());
            userDto.setAbout(post.getUser().getAbout());

            postDto.setUser(userDto);
        } else {
            postDto.setUser(null);
        }
        
        Set<CommentDto> commentDtos = new HashSet<>();
        for (Comment comment : post.getComments()) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDtos.add(commentDto);
        }
        postDto.setComments(commentDtos);

        return postDto;
    }
    
    public static Post postDtoToPost(PostDto postDto) {
        Post post = new Post();

        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());

        if (postDto.getCategory() != null) {
            Category category = new Category();
            category.setCategoryId(postDto.getCategory().getCategoryId());
            category.setCategoryTitle(postDto.getCategory().getCategoryTitle());
            category.setCategoryDescription(postDto.getCategory().getCategoryDescription());
            post.setCategory(category);
        } else {
            post.setCategory(null); 
        }

        if (postDto.getUser() != null) {
            User user = new User();
            user.setId(postDto.getUser().getId());
            user.setName(postDto.getUser().getName());
            user.setEmail(postDto.getUser().getEmail());
            user.setPassword(postDto.getUser().getPassword());
            user.setAbout(postDto.getUser().getAbout());
            post.setUser(user);
        } 

        Set<Comment> comments = new HashSet<>();
        for (CommentDto commentDto : postDto.getComments()) {
            Comment comment = new Comment();
            comment.setId(commentDto.getId());
            comment.setContent(commentDto.getContent());
            comment.setPost(post);  
            comments.add(comment);
        }
        post.setComments(comments);

        return post;
    }

    public static List<PostDto> postListToPostDtoList(List<Post> posts) {
        return posts.stream()
                    .map(PostMapper::postToPostDto)  
                    .collect(Collectors.toList());
    }
    
    public static List<Post> postDtoListToPostList(List<PostDto> postDtos) {
        return postDtos.stream()
                       .map(PostMapper::postDtoToPost)  
                       .collect(Collectors.toList());
    }
}
