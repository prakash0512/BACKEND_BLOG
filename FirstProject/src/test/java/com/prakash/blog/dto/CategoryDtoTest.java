package com.prakash.blog.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import javax.xml.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDtoTest {

    @Test
    void testCategoryDtoValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        jakarta.validation.Validator validator = factory.getValidator();
        
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("Cat");
        categoryDto.setCategoryDescription("Short desc");
        
      
    }

    @Test
    void testCategoryDtoValid() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("Valid Category");
        categoryDto.setCategoryDescription("This is a valid description");
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    
        assertTrue(violations.isEmpty());
    }
}
