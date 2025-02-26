package com.prakash.blog.mapper;

import com.prakash.blog.dto.RoleDto;
import com.prakash.blog.entites.Role;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleMapperTest {

    @Test
    void testMapToDto() {
       
        Role role = new Role();
        role.setId(1);
        role.setName("Admin");

        
        RoleDto roleDto = RoleMapper.mapToDto(role);

      
        assertNotNull(roleDto);
        assertEquals(role.getId(), roleDto.getId());
        assertEquals(role.getName(), roleDto.getName());
    }

    @Test
    void testMapToDtoNullRole() {
        // Mapping null Role
        RoleDto roleDto = RoleMapper.mapToDto(null);

        // Verifying that null is returned
        assertNull(roleDto);
    }

    @Test
    void testMapToEntity() {
        // Creating a RoleDto
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setName("Admin");

       
        Role role = RoleMapper.mapToEntity(roleDto);

       
        assertNotNull(role);
        assertEquals(roleDto.getId(), role.getId());
        assertEquals(roleDto.getName(), role.getName());
    }

    @Test
    void testMapToEntityNullRoleDto() {
       
        Role role = RoleMapper.mapToEntity(null);

        
        assertNull(role);
    }
}

