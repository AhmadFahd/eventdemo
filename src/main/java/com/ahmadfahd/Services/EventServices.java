package com.ahmadfahd.Services;

import com.ahmadfahd.dto.EventsDTO;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.TicketsEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventServices {

    List<EventsDTO> getAllEvents() ;
    EventsDTO findById(Long eventid) ;
    void addEvent(EventsDTO eventsDTO, Long userid);
    void updateEvent(EventsDTO eventsDTO,Long eventid);
    void deleteById(Long eventid);
    void eventApprove(Long eventid, boolean aproved);
//    List<EventsDTO> findByCity(String eventcity);
//    List<EventsDTO> findByDate(LocalDate eventdate);
    List<EventsDTO> getAllActive();
//    EventsDTO findIfPresent(Long eventid);
    Map getOrganizer(long eventid);
    List<EventsDTO> findByUser(Long id);
    List<EventsDTO> getNonApproved(Long id);
    List<EventsDTO> getAllNonApproved();

}
