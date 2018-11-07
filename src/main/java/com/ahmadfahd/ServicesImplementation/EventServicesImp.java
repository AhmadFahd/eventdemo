package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.EventServices;
import com.ahmadfahd.dto.EventsDTO;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServicesImp implements EventServices {

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public List<EventsEntity> getAllEvents() {
        return eventsRepository.findAll();
    }

    @Override
    public Optional<EventsEntity> findById(Long eventid) {
        return eventsRepository.findById(eventid);
    }

    @Override
    public EventsEntity addEvent(EventsDTO eventsDTO, Long userid) {

        EventsEntity eventsEntity;
        eventsEntity = modelMapper.map(eventsDTO, EventsEntity.class);
        eventsEntity.setOrganizer(usersRepository.findById(userid).get());
        return eventsRepository.save(eventsEntity);
    }

    @Override
    public EventsEntity updateEvent(EventsDTO eventsDTO, Long eventid) {
        EventsEntity eventsEntity1 = eventsRepository.findById(eventid).get();
        EventsEntity eventsEntity;
        eventsEntity = modelMapper.map(eventsDTO, EventsEntity.class);
        eventsEntity.setEventid(eventsEntity1.getEventid());
        eventsEntity.setOrganizer(eventsEntity1.getOrganizer());
        eventsEntity.setComments(eventsEntity1.getComments());
        return eventsRepository.save(eventsEntity);
    }

    @Override
    public EventsEntity deleteById(Long eventid) {
        EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
        eventsEntity.setDeleted(true);
        return eventsRepository.save(eventsEntity);
    }

    @Override
    public EventsEntity eventAprrove(Long eventid, boolean aproved) {
        //you can use optional and delete get();
        EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
        eventsEntity.setApproved(aproved);
        return eventsRepository.save(eventsEntity);
    }

    @Override
    public List<EventsEntity> findByCity(String eventcity) {
        return eventsRepository.findAllByEventcity(eventcity);
    }

    @Override
    public List<EventsEntity> findByDate(LocalDate eventdate) {
        return eventsRepository.findByEventdate(eventdate);
    }


    @Override
    public List<EventsEntity> getAllActive() {
        LocalDate localDate = LocalDate.now();
        return eventsRepository.findByDeletedFalseAndApprovedTrueAndEventdateAfter(localDate);
    }

    @Override
    public Optional<EventsEntity> findIfPresent(Long eventid) {
        LocalDate date = LocalDate.now();
        return eventsRepository.findByEventidAndDeletedFalseAndApprovedTrueAndEventdateAfter(eventid, date);
    }

}