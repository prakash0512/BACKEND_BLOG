package com.prakash.blog.mapper;

import com.prakash.blog.dto.UserDto;
import com.prakash.blog.entites.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void testEntityToDto() {
        User user = new User();
        user.setId(1);
        user.setName("Prakash");
        user.setEmail("prakash@example.com");
        user.setPassword("password123");
        user.setAbout("A passionate developer.");

        UserDto userDto = UserMapper.entityToDto(user);

        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getAbout(), userDto.getAbout());
    }

    @Test
    void testEntityToDtoNull() {
        UserDto userDto = UserMapper.entityToDto(null);
        assertNull(userDto);
    }

    @Test
    void testDtoToEntity() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("Prakash");
        userDto.setEmail("prakash@example.com");
        userDto.setPassword("password123");
        userDto.setAbout("A passionate developer.");

        User user = UserMapper.dtoToEntity(userDto);

        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getAbout(), user.getAbout());
    }

    @Test
    void testDtoToEntityNull() {
        User user = UserMapper.dtoToEntity(null);
        assertNull(user);
    }
}
