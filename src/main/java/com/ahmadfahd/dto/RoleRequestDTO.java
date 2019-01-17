package com.ahmadfahd.dto;

import com.ahmadfahd.entity.UsersEntity;

public class RoleRequestDTO {
    private Long id;
    private UserGetDto user;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserGetDto getUser() {
        return user;
    }

    public void setUser(UserGetDto user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
