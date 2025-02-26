package com.prakash.blog.service;

import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.entites.Category;
import com.prakash.blog.exception.ResourceNotFoundException;
import com.prakash.blog.mapper.CategoryMapper;
import com.prakash.blog.repo.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("Tech");
        categoryDto.setCategoryDescription("Technology Posts");
    }

    @Test
    void createCategory() {
        Category category = CategoryMapper.dtoToEntity(categoryDto);
        when(categoryRepo.save(any())).thenReturn(category);

        CategoryDto createdCategory = categoryService.createCategory(categoryDto);

        assertEquals(categoryDto.getCategoryTitle(), createdCategory.getCategoryTitle());
        verify(categoryRepo, times(1)).save(any());
    }

    @Test
    void updateCategory() {
        Category category = new Category();
        category.setCategoryTitle("Old Title");
        when(categoryRepo.findById(1)).thenReturn(java.util.Optional.of(category));

        categoryDto.setCategoryTitle("Updated Title");
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, 1);

       assertEquals("Updated Title", updatedCategory.getCategoryTitle());
    }

    @Test
    void deleteCategory() {
        Category category = new Category();
        when(categoryRepo.findById(1)).thenReturn(java.util.Optional.of(category));

        categoryService.deleteCategory(1);

        verify(categoryRepo, times(1)).delete(category);
    }

    @Test
    void getCategory_notFound() {
        when(categoryRepo.findById(1)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategory(1));
    }
}
