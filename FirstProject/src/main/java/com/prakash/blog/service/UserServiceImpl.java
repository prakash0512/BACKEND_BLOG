package com.prakash.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prakash.blog.config.AppConstants;
import com.prakash.blog.dto.UserDto;
import com.prakash.blog.entites.User;
import com.prakash.blog.exception.ResourceNotFoundException;
import com.prakash.blog.mapper.UserMapper;
import com.prakash.blog.repo.RoleRepo;
import com.prakash.blog.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	@Override
	public UserDto createUser(UserDto userdto) {
		User user= UserMapper.dtoToEntity(userdto);
		User Saved=this.userRepo.save(user);
		UserDto Saveduser=UserMapper.entityToDto(Saved);
		return Saveduser;	
		
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer id) {
		
		User user = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
		
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setName(userdto.getName());
		user.setPassword(userdto.getPassword());
		
		this.userRepo.save(user);
		UserDto updated=UserMapper.entityToDto(user);
		
		
		return updated;
	}

	@Override
	public UserDto getUserById(Integer id) {
		
		User user = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
		
		UserDto userdetail=UserMapper.entityToDto(user);
		
		return userdetail;
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users = this.userRepo.findAll();
		//List<UserDto> userdto=new ArrayList();
		
		List<UserDto> userDtos = users.stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList());
		return userDtos;
	
	}

	@Override
	public void deleteUser(Integer id) {
		

		User user = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
		
		 this.userRepo.delete(user);
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = UserMapper.dtoToEntity(userDto);
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return UserMapper.entityToDto(newUser);
	}

	

}
