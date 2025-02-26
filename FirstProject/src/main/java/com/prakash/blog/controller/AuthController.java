package com.prakash.blog.controller;

import java.security.Principal;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.blog.dto.UserDto;
import com.prakash.blog.entites.User;
import com.prakash.blog.exception.ApiException;
import com.prakash.blog.mapper.JwtAuthResponse;
import com.prakash.blog.mapper.UserMapper;
import com.prakash.blog.mapper.jwtAuthRequest;
import com.prakash.blog.repo.UserRepo;
import com.prakash.blog.security.JwtTokenHelper;
import com.prakash.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody jwtAuthRequest request) throws Exception {
	    authenticate(request.getUsername(), request.getPassword());

	    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
	    String token = jwtTokenHelper.generateToken(userDetails);

	    User user = (User) userDetails; 
	    UserDto userDto = UserMapper.entityToDto(user); 

	    JwtAuthResponse response = new JwtAuthResponse();
	    response.setToken(token);
	    response.setUser(userDto);

	    return ResponseEntity.ok(response);
	}

	
	

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
	UserDto registeredUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

	
	@Autowired
	private UserRepo userRepo;
	

	@GetMapping("/current-user/")
	public ResponseEntity<UserDto> getUser(Principal principal) {
		User user = this.userRepo.findByEmail(principal.getName()).get();
		return new ResponseEntity<UserDto>(UserMapper.entityToDto(user), HttpStatus.OK);
	}

}
