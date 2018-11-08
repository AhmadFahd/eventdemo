package com.ahmadfahd.Services;

import com.ahmadfahd.dto.EventsDTO;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.TicketsEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventServices {

    ResponseEntity getAllEvents() ;
    ResponseEntity findById(Long eventid) ;
    ResponseEntity addEvent(EventsDTO eventsDTO, Long userid);
    ResponseEntity updateEvent(EventsDTO eventsDTO,Long eventid);
    ResponseEntity deleteById(Long eventid);
    ResponseEntity eventAprrove(Long eventid,boolean aproved);
    ResponseEntity findByCity(String eventcity);
    ResponseEntity findByDate(LocalDate eventdate);
    ResponseEntity getAllActive();
    ResponseEntity findIfPresent(Long eventid);


    }
