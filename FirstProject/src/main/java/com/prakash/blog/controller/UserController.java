package com.prakash.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.blog.dto.ApiResponse;
import com.prakash.blog.dto.UserDto;
import com.prakash.blog.service.UserService;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userdto){
	UserDto user=this.userservice.createUser(userdto);
		return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
	}
	
	@GetMapping("/getByid/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("id") Integer userId) {
	    return ResponseEntity.ok(this.userservice.getUserById(userId));
	}

	
	@GetMapping("/getall")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userservice.getAllUser());
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer uid) {
		this.userservice.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
	}
	

	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer uid) {
		UserDto updatedUser = this.userservice.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	

}
