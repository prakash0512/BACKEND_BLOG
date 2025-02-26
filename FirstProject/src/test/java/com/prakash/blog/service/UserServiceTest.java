package com.prakash.blog.service;

import com.prakash.blog.dto.UserDto;
import com.prakash.blog.mapper.UserMapper;
import com.prakash.blog.repo.UserRepo;
import com.prakash.blog.entites.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("Prakash");
        userDto.setAbout("Test user about Prakash");
        userDto.setEmail("prakash@example.com");
        userDto.setPassword("test123");

        user = new User();
        user.setId(1);
        user.setName("Prakash");
        user.setAbout("Test user about Prakash");
        user.setEmail("prakash@example.com");
        user.setPassword("test123");
    }

    @Test
    void createUserTest() {
        when(userRepo.save(any(User.class))).thenReturn(user);

        UserDto createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
        assertEquals("Prakash", createdUser.getName());
        assertEquals("prakash@example.com", createdUser.getEmail());
    }

    @Test
    void updateUserTest() {
        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepo.save(any(User.class))).thenReturn(user);

        UserDto updatedUser = userService.updateUser(userDto, 1);

        assertNotNull(updatedUser);
        assertEquals("Prakash", updatedUser.getName());
        assertEquals("prakash@example.com", updatedUser.getEmail());
    }

    @Test
    void getUserByIdTest() {
        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));

        UserDto foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals("Prakash", foundUser.getName());
    }

    @Test
    void getAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepo.findAll()).thenReturn(users);

        List<UserDto> userList = userService.getAllUser();

        assertNotNull(userList);
        assertEquals(1, userList.size());
        assertEquals("Prakash", userList.get(0).getName());
    }

    @Test
    void deleteUserTest() {
        when(userRepo.findById(anyInt())).thenReturn(Optional.of(user));

        userService.deleteUser(1);

        verify(userRepo, times(1)).delete(any(User.class));
    }
}
