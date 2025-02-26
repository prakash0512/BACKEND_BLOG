package com.prakash.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prakash.blog.entites.Role;


public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
