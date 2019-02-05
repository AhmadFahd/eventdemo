package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/add/{uid}/{eid}")
    public ResponseEntity addVote(@PathVariable Long uid , @PathVariable Long eid) {
        voteService.addVote(uid,eid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check/{uid}/{eid}")
    public ResponseEntity check(@PathVariable Long uid , @PathVariable Long eid) {
        return ResponseEntity.ok(voteService.isVoted(uid,eid));
    }
    @GetMapping("/{eid}")
    public ResponseEntity count(@PathVariable Long eid){
        return ResponseEntity.ok(voteService.numOfVotes(eid));
    }


}
