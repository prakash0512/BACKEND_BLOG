package com.prakash.blog.controller;

import com.prakash.blog.dto.UserDto;
import com.prakash.blog.mapper.JwtAuthResponse;
import com.prakash.blog.mapper.jwtAuthRequest;
import com.prakash.blog.security.JwtTokenHelper;
import com.prakash.blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    @Mock
    private JwtTokenHelper jwtTokenHelper;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    private jwtAuthRequest authRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authRequest = new jwtAuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password");
    }

    @Test
    void createToken() throws Exception {
        String token = "dummy-token";
        UserDto userDto = new UserDto();
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        jwtAuthResponse.setUser(userDto);

        when(jwtTokenHelper.generateToken(any())).thenReturn(token);

        ResponseEntity<JwtAuthResponse> response = authController.createToken(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getToken());
    }

    @Test
    void getUser() {
        UserDto userDto = new UserDto();
        userDto.setName("testuser");

        when(userService.getUserById(any())).thenReturn(userDto);

       ResponseEntity<UserDto> response = authController.getUser(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }
}
