package com.ahmadfahd.Services;

import com.ahmadfahd.dto.EventsDTO;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.TicketsEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventServices {

    List<EventsEntity> getAllEvents() ;
    Optional<EventsEntity> findById(Long eventid) ;
    EventsEntity addEvent(EventsDTO eventsDTO,Long userid);
    EventsEntity updateEvent(EventsDTO eventsDTO,Long eventid);
    EventsEntity deleteById(Long eventid);
    EventsEntity eventAprrove(Long eventid,boolean aproved);
    List<EventsEntity> findByCity(String eventcity);
    List<EventsEntity> findByDate(LocalDate eventdate);
    List<EventsEntity> getAllActive();
    Optional<EventsEntity> findIfPresent(Long eventid);


    }
