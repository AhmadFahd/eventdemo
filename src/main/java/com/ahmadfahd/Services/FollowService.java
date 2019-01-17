package com.ahmadfahd.Services;

import com.ahmadfahd.dto.FollowDTO;
import com.ahmadfahd.dto.UserGetDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface FollowService {


    void follow(Long uid,Long fid);
    List<FollowDTO> myFollowing(Long uid);
    List<FollowDTO> myFollowers(Long fid);
    void unFollow(Long uid,Long fid);
    boolean isFollowed(Long uid,Long fid);
    List<UserGetDto> myFollowingList(Long uid);
    }
