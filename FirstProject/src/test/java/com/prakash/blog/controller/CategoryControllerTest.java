package com.prakash.blog.controller;

import com.prakash.blog.dto.ApiResponse;
import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("Technology");
        categoryDto.setCategoryDescription("Tech-related posts");
    }

    @Test
    void createCategory() {
        when(categoryService.createCategory(categoryDto)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.createCategory(categoryDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    void updateCategory() {
        when(categoryService.updateCategory(categoryDto, 1)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.updateCategory(categoryDto, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    void deleteCategory() {
        doNothing().when(categoryService).deleteCategory(1);

        ResponseEntity<ApiResponse> response = categoryController.deleteCategory(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("category is deleted successfully !!", response.getBody().getMessage());
    }
}

