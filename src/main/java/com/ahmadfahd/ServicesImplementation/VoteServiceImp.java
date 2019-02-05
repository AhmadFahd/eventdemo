package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.VoteService;
import com.ahmadfahd.entity.VoteEntity;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.UsersRepository;
import com.ahmadfahd.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImp implements VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public boolean isVoted(Long userId,Long eventId) {
        return voteRepository.existsByUserIdAndEventId(userId,eventId);
    }

    @Override
    public long numOfVotes(Long eid) {
        return voteRepository.countByEventId(eid);
    }

    @Override
    public void addVote(Long userId, Long eventId) {
        if(voteRepository.existsByUserIdAndEventId(userId,eventId))
        {
            throw new RuntimeException("User has already voted this survey");
        }
            VoteEntity voteEntity = new VoteEntity();
            voteEntity.setUser(usersRepository.findById(userId).get());
            voteEntity.setEvent(eventsRepository.findById(eventId).get());
            voteRepository.save(voteEntity);
    }
}
