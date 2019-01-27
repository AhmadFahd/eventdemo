package com.ahmadfahd.dto;

import com.ahmadfahd.entity.UsersEntity;

import java.util.HashMap;
import java.util.Map;

public class FollowDTO {
    private Long id;
    private UserGetDto user;
    private UserGetDto followed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserGetDto getUser() { return user; }

    public void setUser(UserGetDto user) { this.user = user; }

    public UserGetDto getFollowed() {
        return followed;
    }

    public void setFollowed(UserGetDto followed) {
        this.followed = followed;
    }
}