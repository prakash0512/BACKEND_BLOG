package com.prakash.blog.mapper;

import com.prakash.blog.dto.UserDto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserDto user;
}

