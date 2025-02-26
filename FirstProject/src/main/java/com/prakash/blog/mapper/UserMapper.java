package com.prakash.blog.mapper;

import com.prakash.blog.dto.UserDto;
import com.prakash.blog.entites.User;

public class UserMapper {

	
	 public static UserDto entityToDto(User user) {
	        if (user == null) {
	            return null;
	        }

	        UserDto userDto = new UserDto();
	        userDto.setId(user.getId());
	        userDto.setName(user.getName());
	        userDto.setAbout(user.getAbout());
	        userDto.setEmail(user.getEmail());
	        userDto.setPassword(user.getPassword());

	        return userDto;
	    }

	   
	    public static User dtoToEntity(UserDto userDto) {
	        if (userDto == null) {
	            return null;
	        }

	        User user = new User();
	        user.setId(userDto.getId());
	        user.setName(userDto.getName());
	        user.setAbout(userDto.getAbout());
	        user.setEmail(userDto.getEmail());
	        user.setPassword(userDto.getPassword());

	        return user;
	    }
}
