package com.prakash.blog.mapper;

import com.prakash.blog.dto.RoleDto;
import com.prakash.blog.entites.Role;


public class RoleMapper {

    public static RoleDto mapToDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

   
    public static Role mapToEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}

