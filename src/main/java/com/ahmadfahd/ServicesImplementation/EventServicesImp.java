package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.EventServices;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.repository.EventsRepository;
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


    @Override
    public Iterable<EventsEntity> getAllEvents() {
        return eventsRepository.findAll();
    }

    @Override
    public Optional<EventsEntity> findById(Long eventid) {
        return eventsRepository.findById(eventid);
    }

    @Override
    public void addEvent(EventsEntity eventsEntity) {
        eventsRepository.save(eventsEntity);
    }

    @Override
    public void updateEvent(EventsEntity eventsEntity, String eventid) {
        eventsRepository.save(eventsEntity); }

    @Override
    public void deleteById(Long eventid) {
        EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
        eventsEntity.setDeleted(true);
        eventsRepository.save(eventsEntity); }

    @Override
    public void eventAprrove(Long eventid, boolean aproved) {
        //you can use optional and delete get();
        EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
        eventsEntity.setApproved(aproved);
        eventsRepository.save(eventsEntity); }

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
    public Optional<EventsEntity> findIfPresent(Long eventid){
        LocalDate date= LocalDate.now();
        return eventsRepository.findByEventidAndDeletedFalseAndApprovedTrueAndEventdateAfter(eventid,date);
    }

    @Override
    public Long HowManyAproved() {
        return eventsRepository.countByApprovedTrue();
    }
}
