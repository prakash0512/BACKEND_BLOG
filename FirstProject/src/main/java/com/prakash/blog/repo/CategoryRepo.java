package com.prakash.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakash.blog.entites.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
