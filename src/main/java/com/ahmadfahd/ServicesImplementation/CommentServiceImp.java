package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.CommentService;
import com.ahmadfahd.entity.CommentsEntity;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.CommentsRepository;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void AddComment(CommentsEntity commentsEntity, Long eventid, Long userid) {
        Optional<EventsEntity> eventsEntityOptional = eventsRepository.findByEventidAndDeletedFalseAndApprovedTrue(eventid);
        Optional<UsersEntity> usersEntityOptional = usersRepository.findById(userid);
        if (eventsEntityOptional.isPresent() && usersEntityOptional.isPresent()) {
            EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
            UsersEntity usersEntity = usersRepository.findById(userid).get();
            LocalDateTime timer = LocalDateTime.now().minusMinutes(1);
            long counter = commentsRepository.countByTheeventAndUseridAndCommenttimeAfter(eventsEntity,usersEntity,timer);
                if(counter==0){
                commentsEntity.setTheevent(eventsEntity);
                commentsEntity.setUserid(usersEntity);
                commentsEntity.setCommenttime(LocalDateTime.now());
                commentsRepository.save(commentsEntity);
            }
        }

    }

}
