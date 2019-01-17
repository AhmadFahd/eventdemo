package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.FollowService;
import com.ahmadfahd.dto.FollowDTO;
import com.ahmadfahd.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/{uid}/{fid}")
    public ResponseEntity follow(@PathVariable Long uid,@PathVariable Long fid){
        followService.follow(uid,fid);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/following/{uid}")
    public ResponseEntity getFollowing(@PathVariable Long uid){
        return ResponseEntity.ok(followService.myFollowing(uid));
    }
    @GetMapping("/followers/{fid}")
    public ResponseEntity getFollowers(@PathVariable Long fid){
        return ResponseEntity.ok(followService.myFollowers(fid));
    }
    @GetMapping("/cancel/{uid}/{fid}")
    public ResponseEntity unFollow(@PathVariable Long uid,@PathVariable Long fid){
        followService.unFollow(uid,fid);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/isFollowed/{uid}/{fid}")
    public ResponseEntity isFollowed(@PathVariable Long uid,@PathVariable Long fid) {
        return ResponseEntity.ok(followService.isFollowed(uid,fid));
    }
    @GetMapping("/followinglist/{uid}")
    public ResponseEntity listOfFollowing(@PathVariable Long uid){
        return ResponseEntity.ok(followService.myFollowingList(uid));
    }



}
