package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.EventServices;
import com.ahmadfahd.entity.EventsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@Valid
public class EventController {

    @Autowired
    private EventServices eventServices;
    @Autowired
    private NotificationService notificationService;

     @GetMapping("/UsersAccess/view")
    public List<EventsEntity> getAllActive(){ return eventServices.getAllActive(); }

    @GetMapping("/AdminAccess/view")
    public Iterable<EventsEntity> getAllEvents(){ return eventServices.getAllEvents(); }

    @GetMapping("/view/{eventid}")
    public Optional<EventsEntity> findById(@PathVariable Long eventid){
        return eventServices.findById(eventid);
    }

    @PostMapping("/add")
    public void addEvent(@RequestBody @Valid EventsEntity eventsEntity){ eventServices.addEvent(eventsEntity); }

    @PutMapping("/edit/{eventid}")
    public void updateEvent(@RequestBody EventsEntity eventsEntity, @PathVariable String eventid) {
        eventServices.updateEvent(eventsEntity,eventid);
        notificationService.updateEventNotification(Long.valueOf(eventid));}

    @PutMapping("/delete/{eventid}")
    public void deleteById(@PathVariable Long eventid){
         eventServices.deleteById(eventid);
     notificationService.eventCancelNotification(eventid);
     }

    @PutMapping("/AdminAccess/approve/{eventid}")
    public void eventAprrove(@PathVariable Long eventid){ eventServices.eventAprrove(eventid,true); }

    @PutMapping("/AdminAccess/unapprove/{eventid}")
    public void eventUnaprrove(@PathVariable Long eventid){ eventServices.eventAprrove(eventid,false); }


    @GetMapping("/AdminAccess/viewbylocation/{eventcity}")
    public List<EventsEntity> findAllByEventlocation(@PathVariable String eventcity) {
        return eventServices.findByCity(eventcity);
    }
    @GetMapping("/AdminAccess/viewBydate/{eventdate}")
    public List<EventsEntity> findAllByEventdate(@PathVariable String eventdate) {
        return eventServices.findByDate(LocalDate.parse(eventdate));}


    @GetMapping("/viewifpresent/{eventid}")
    public Optional<EventsEntity> findIfPresent(@PathVariable Long eventid){
    return eventServices.findIfPresent(eventid);
   }

   @GetMapping("/count")
   public Long HowManyAproved() {
         return eventServices.HowManyAproved();
   }

   }






