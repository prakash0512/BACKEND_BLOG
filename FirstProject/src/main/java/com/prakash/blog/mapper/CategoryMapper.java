/*package com.prakash.blog.mapper;

import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.entites.Category;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    
    public static CategoryDto entityToDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryTitle(category.getCategoryTitle());
        dto.setCategoryDescription(category.getCategoryDescription());
        return dto;
    }

   
    public static Category dtoToEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        category.setCategoryTitle(dto.getCategoryTitle());
        category.setCategoryDescription(dto.getCategoryDescription());
        return category;
    }

    
    public static List<CategoryDto> entityToDtoList(List<Category> categories) {
        return categories.stream().map(CategoryMapper::entityToDto).collect(Collectors.toList());
    }

    
    public static List<Category> dtoToEntityList(List<CategoryDto> dtos) {
        return dtos.stream().map(CategoryMapper::dtoToEntity).collect(Collectors.toList());
    }
}
*/


package com.prakash.blog.mapper;

import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.entites.Category;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDto entityToDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryTitle(category.getCategoryTitle());
        dto.setCategoryDescription(category.getCategoryDescription());
        return dto;
    }

    public static Category dtoToEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        category.setCategoryTitle(dto.getCategoryTitle());
        category.setCategoryDescription(dto.getCategoryDescription());
        return category;
    }

    public static List<CategoryDto> entityToDtoList(List<Category> categories) {
        return categories.stream().map(CategoryMapper::entityToDto).collect(Collectors.toList());
    }

    public static List<Category> dtoToEntityList(List<CategoryDto> dtos) {
        return dtos.stream().map(CategoryMapper::dtoToEntity).collect(Collectors.toList());
    }
}

