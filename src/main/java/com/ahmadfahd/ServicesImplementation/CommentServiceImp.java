package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.CommentService;
import com.ahmadfahd.dto.CommentsDTO;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.entity.CommentsEntity;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.enums.ACTIONS;
import com.ahmadfahd.repository.CommentsRepository;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.FeedRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FeedRepository feedRepository;

    @Override
    public void AddComment(CommentsDTO commentsDTO, Long eventid, Long userid) {
        Optional<EventsEntity> eventsEntityOptional = eventsRepository.findByIdAndDeletedFalseAndApprovedTrue(eventid);
        Optional<UsersEntity> usersEntityOptional = usersRepository.findById(userid);
        if (eventsEntityOptional.isPresent() && usersEntityOptional.isPresent()) {
            CommentsEntity commentsEntity = modelMapper.map(commentsDTO, CommentsEntity.class);
            EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
            UsersEntity usersEntity = usersRepository.findById(userid).get();
            LocalDateTime timer = LocalDateTime.now().minusMinutes(1);
            boolean counter = commentsRepository.existsByEventAndUserAndTimeAfter(eventsEntity, usersEntity, timer);
            if (!counter) {
                commentsEntity.setEvent(eventsEntity);
                commentsEntity.setUser(usersEntity);
                commentsEntity.setTime(LocalDateTime.now());
                FeedEntity feedEntity = new FeedEntity();
                feedEntity.setAction(ACTIONS.COMMENT.toString());
                feedEntity.setUser(usersEntity);

                feedEntity.setTime(LocalDateTime.now());
                commentsRepository.save(commentsEntity);
                feedEntity.setTargetComment(commentsEntity);
                feedRepository.save(feedEntity);
            }
        }
    }

    public List<CommentsDTO> findTheComments(Long eid) {
        List<CommentsEntity> commentsEntityList = commentsRepository.findByEventAndDeletedFalse(eventsRepository.findById(eid).get());
        List<CommentsDTO> commentsDTOList = ObjectMapperUtils.mapAll(commentsEntityList, CommentsDTO.class);
        return commentsDTOList;
    }
}