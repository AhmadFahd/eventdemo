package com.ahmadfahd.dto;

import com.ahmadfahd.entity.UsersEntity;

import java.util.HashMap;
import java.util.Map;

public class FollowDTO {
    private Long id;
    private UsersEntity user;
    private UsersEntity followed;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Map getUser() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        return map; }

    public void setUser(UsersEntity user) { this.user = user; }

    public Map getFollowed() { Map<String,Object> map = new HashMap<>();
        map.put("id",followed.getId());
        map.put("username",followed.getUsername());
        return map; }

    public void setFollowed(UsersEntity followed) { this.followed = followed; }
}
