package com.ahmadfahd.controllers;


import com.ahmadfahd.Services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feeds")
public class FeedsController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/myfeeds/{uid}")
    public ResponseEntity findMyFeeds(@PathVariable Long uid)
    {
        return ResponseEntity.ok(feedService.findMyFeeds(uid));
    }
    @GetMapping("/{uid}")
    public ResponseEntity findUserFeeds(@PathVariable Long uid)
    {
        return ResponseEntity.ok(feedService.findUsersFeeds(uid));
    }
}
