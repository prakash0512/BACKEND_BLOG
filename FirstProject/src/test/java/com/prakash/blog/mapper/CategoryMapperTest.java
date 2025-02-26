package com.prakash.blog.mapper;

import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.entites.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class CategoryMapperTest {

    @Test
    void testEntityToDto() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryTitle("Tech");
        category.setCategoryDescription("Tech related posts");

        CategoryDto categoryDto = CategoryMapper.entityToDto(category);

        assertNotNull(categoryDto);
        assertEquals(category.getCategoryId(), categoryDto.getCategoryId());
        assertEquals(category.getCategoryTitle(), categoryDto.getCategoryTitle());
        assertEquals(category.getCategoryDescription(), categoryDto.getCategoryDescription());
    }

    @Test
    void testEntityToDtoNull() {
        CategoryDto categoryDto = CategoryMapper.entityToDto(null);
        assertNull(categoryDto);
    }

    @Test
    void testDtoToEntity() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryTitle("Tech");
        categoryDto.setCategoryDescription("Tech related posts");

        Category category = CategoryMapper.dtoToEntity(categoryDto);

        assertNotNull(category);
        assertEquals(categoryDto.getCategoryId(), category.getCategoryId());
        assertEquals(categoryDto.getCategoryTitle(), category.getCategoryTitle());
        assertEquals(categoryDto.getCategoryDescription(), category.getCategoryDescription());
    }

    @Test
    void testDtoToEntityNull() {
        Category category = CategoryMapper.dtoToEntity(null);
        assertNull(category);
    }

    @Test
    void testEntityToDtoList() {
        Category category1 = new Category();
        category1.setCategoryId(1);
        category1.setCategoryTitle("Tech");
        category1.setCategoryDescription("Tech related posts");

        Category category2 = new Category();
        category2.setCategoryId(2);
        category2.setCategoryTitle("Lifestyle");
        category2.setCategoryDescription("Lifestyle related posts");

        List<Category> categories = Arrays.asList(category1, category2);

        List<CategoryDto> categoryDtos = CategoryMapper.entityToDtoList(categories);

        assertEquals(2, categoryDtos.size());
        assertEquals(category1.getCategoryId(), categoryDtos.get(0).getCategoryId());
        assertEquals(category2.getCategoryId(), categoryDtos.get(1).getCategoryId());
    }

    @Test
    void testDtoToEntityList() {
        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryId(1);
        categoryDto1.setCategoryTitle("Tech");
        categoryDto1.setCategoryDescription("Tech related posts");

        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto2.setCategoryId(2);
        categoryDto2.setCategoryTitle("Lifestyle");
        categoryDto2.setCategoryDescription("Lifestyle related posts");

        List<CategoryDto> categoryDtos = Arrays.asList(categoryDto1, categoryDto2);

        List<Category> categories = CategoryMapper.dtoToEntityList(categoryDtos);

        assertEquals(2, categories.size());
        assertEquals(categoryDto1.getCategoryId(), categories.get(0).getCategoryId());
        assertEquals(categoryDto2.getCategoryId(), categories.get(1).getCategoryId());
    }
}
