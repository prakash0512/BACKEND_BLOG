package com.prakash.blog.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleDtoTest {

    @Test
    void testRoleDto() {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setName("Admin");
        
        assertEquals(1, roleDto.getId());
        assertEquals("Admin", roleDto.getName());
    }
}
