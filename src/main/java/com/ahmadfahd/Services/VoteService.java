package com.ahmadfahd.Services;

public interface VoteService {

    boolean isVoted(Long userId,Long eventId);
    long numOfVotes(Long eid);
    void addVote(Long userId,Long eventId);

}
