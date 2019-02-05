package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.VoteService;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.entity.VoteEntity;
import com.ahmadfahd.enums.ACTIONS;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.FeedRepository;
import com.ahmadfahd.repository.UsersRepository;
import com.ahmadfahd.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteServiceImp implements VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private FeedRepository feedRepository;

    @Override
    public boolean isVoted(Long userId, Long eventId) {
        return voteRepository.existsByUserIdAndEventId(userId, eventId);
    }

    @Override
    public long numOfVotes(Long eid) {
        return voteRepository.countByEventId(eid);
    }

    @Override
    public void addVote(Long userId, Long eventId) {
        if (voteRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new RuntimeException("User has already voted this survey");
        }
        VoteEntity voteEntity = new VoteEntity();
        UsersEntity usersEntity = usersRepository.findById(userId).get();
        EventsEntity eventsEntity = eventsRepository.findById(eventId).get();
        voteEntity.setUser(usersEntity);
        voteEntity.setEvent(eventsEntity);
        voteRepository.save(voteEntity);
        FeedEntity feedEntity = new FeedEntity();
        feedEntity.setUser(usersEntity);
        feedEntity.setTargetEvent(eventsEntity);
        feedEntity.setAction(ACTIONS.VOTE.toString());
        feedEntity.setTime(LocalDateTime.now());
        feedRepository.save(feedEntity);

    }
}
