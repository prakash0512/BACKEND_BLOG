package com.prakash.blog.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prakash.blog.entites.User;


public interface UserRepo extends JpaRepository<User,Integer> {

	
	Optional<User> findByEmail(String email);
	
}
