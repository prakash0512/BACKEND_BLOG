package com.prakash.blog.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prakash.blog.dto.UserDto;
import com.prakash.blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Prakash Kumar");
        userDto.setEmail("prakash.kumar@example.com");
        userDto.setPassword("password123");

        UserDto savedUserDto = new UserDto();
        savedUserDto.setId(1);
        savedUserDto.setName("Prakash Kumar");
        savedUserDto.setEmail("prakash.kumar@example.com");
        savedUserDto.setPassword("password123");

        when(userService.createUser(any(UserDto.class))).thenReturn(savedUserDto);

        mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Prakash Kumar"));
    }

    @Test
    public void testGetSingleUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("Prakash Kumar");

        when(userService.getUserById(1)).thenReturn(userDto);

        mockMvc.perform(get("/user/getByid/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Prakash Kumar"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1);
        userDto1.setName("Prakash Kumar");

        UserDto userDto2 = new UserDto();
        userDto2.setId(2);
        userDto2.setName("Prakash Kumar");

        List<UserDto> users = List.of(userDto1, userDto2);

        when(userService.getAllUser()).thenReturn(users);

        mockMvc.perform(get("/user/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1);

        mockMvc.perform(delete("/user/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User deleted Successfully"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Prakash Kumar");
        userDto.setEmail("prakash.kumar@example.com");

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setId(1);
        updatedUserDto.setName("Prakash Kumar Updated");
        updatedUserDto.setEmail("prakash.kumar.updated@example.com");

        when(userService.updateUser(any(UserDto.class), eq(1))).thenReturn(updatedUserDto);

        mockMvc.perform(put("/user/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk());
    }
}
