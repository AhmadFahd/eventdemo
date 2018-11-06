package com.ahmadfahd.Services;

import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.TicketsEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventServices {

    Iterable<EventsEntity> getAllEvents() ;
    Optional<EventsEntity> findById(Long eventid) ;
    void addEvent(EventsEntity eventsEntity);
    void updateEvent(EventsEntity eventsEntity, String eventid);
    void deleteById(Long eventid);
    void eventAprrove(Long eventid,boolean aproved);
    List<EventsEntity> findByCity(String eventcity);
    List<EventsEntity> findByDate(LocalDate eventdate);
    List<EventsEntity> getAllActive();
    Optional<EventsEntity> findIfPresent(Long eventid);
    Long HowManyAproved();



    }
