package com.prakash.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.entites.Category;
import com.prakash.blog.exception.ResourceNotFoundException;
import com.prakash.blog.mapper.CategoryMapper;
import com.prakash.blog.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryrepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category =CategoryMapper.dtoToEntity(categoryDto);
		Category created=this.categoryrepo.save(category);
		CategoryDto categorydto=CategoryMapper.entityToDto(created);		
		return categorydto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	
		Category cat = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedcat = this.categoryrepo.save(cat);
		CategoryDto categorydto=CategoryMapper.entityToDto(updatedcat);

		
		return categorydto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));
		this.categoryrepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        CategoryDto categorydto=CategoryMapper.entityToDto(cat);

		
		return categorydto;
	}
	

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories = this.categoryrepo.findAll();
		List<CategoryDto> catDtos = CategoryMapper.entityToDtoList(categories);

		return catDtos;
	}

}
