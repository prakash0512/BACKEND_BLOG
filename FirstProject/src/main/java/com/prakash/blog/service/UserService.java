package com.prakash.blog.service;

import java.util.List;

import com.prakash.blog.dto.UserDto;

public interface UserService {
	
	 UserDto createUser(UserDto userdto);
	 UserDto updateUser(UserDto userdto ,Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
	UserDto registerNewUser(UserDto userDto);
	

}

