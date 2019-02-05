package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.EventServices;
import com.ahmadfahd.dto.EventsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
@Valid
public class EventController {

    private final EventServices eventServices;

    @Autowired
    public EventController(EventServices eventServices) {
        this.eventServices = eventServices;
    }
//    @Autowired
//    private NotificationService notificationService;


    @GetMapping("/active")
    public ResponseEntity getAllActive() {
        if (eventServices.getAllEvents().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventServices.getAllActive());
    }
    @GetMapping("/surveys")
    public ResponseEntity getAllSurveys() {
        if (eventServices.getAllSurveys().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventServices.getAllSurveys());
    }

    @GetMapping("/all")
    public ResponseEntity getAllEvents() {
        if (eventServices.getAllEvents().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventServices.getAllEvents());
    }

    @GetMapping("/view/{eventId}")
    public ResponseEntity findById(@PathVariable Long eventId) {
//        if (!eventServices.findById(eventid).isPresent())
//        {
//            return ResponseEntity.noContent().build();
//        }
        return ResponseEntity.ok(eventServices.findById(eventId));
    }
    @GetMapping("/survey/{eventId}")
    public ResponseEntity findSurveyById(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventServices.findSurveyById(eventId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity addEvent(@RequestBody @Valid EventsDTO eventsDTO, @PathVariable Long userId, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        eventServices.addEvent(eventsDTO, userId);
        return ResponseEntity.created(null).build();
    }
    @PostMapping("/{userId}/add/survey")
    public ResponseEntity addSurvey(@RequestBody @Valid EventsDTO eventsDTO, @PathVariable Long userId, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        eventServices.addSurvey(eventsDTO, userId);
        return ResponseEntity.created(null).build();
    }
    @GetMapping("mysurveys/{userId}")
    public ResponseEntity findMySurveys(@PathVariable Long userId) {
        if
        (eventServices.findMySurveys(userId) != null) {
            return ResponseEntity.ok(eventServices.findMySurveys(userId));
        }
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/edit/{eventId}")
    public ResponseEntity updateEvent(@RequestBody EventsDTO eventsDTO, @PathVariable Long eventId, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        eventServices.updateEvent(eventsDTO, eventId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{eventId}")
    public void deleteById(@PathVariable Long eventId) {
//        notificationService.eventCancelNotification(eventid);
        eventServices.deleteById(eventId);
    }

    @GetMapping("/approve/{eventId}")
    public void eventApprove(@PathVariable Long eventId) {
        eventServices.eventApprove(eventId, true);
    }

    @GetMapping("/unapprove/{eventId}")
    public void eventDisapprove(@PathVariable Long eventId) {
        eventServices.eventApprove(eventId, false);
    }

    @GetMapping("/org/{eventId}")
    public ResponseEntity findOrg(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventServices.getOrganizer(eventId));
    }

    @GetMapping("myevents/{userId}")
    public ResponseEntity findMyEvents(@PathVariable Long userId) {
        if
            (eventServices.findByUser(userId) != null) {
                return ResponseEntity.ok(eventServices.findByUser(userId));
            }
            return ResponseEntity.noContent().build();
        }

    @GetMapping("mynonapproved/{userId}")
    public ResponseEntity findMyNonApprovedEvents(@PathVariable Long userId) {
        if
        (eventServices.getNonApproved(userId) != null) {
            return ResponseEntity.ok(eventServices.getNonApproved(userId));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nonapproved")
    public ResponseEntity getAllUnApproved() {
        if (eventServices.getAllNonApproved() != null) {
        return ResponseEntity.ok(eventServices.getAllNonApproved());
    }
    return ResponseEntity.noContent().build();
    }
    @GetMapping("/isDeleted/{eventId}")
    public ResponseEntity isDeleted(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventServices.isDeleted(eventId));
    }

    @GetMapping("/isSurvey/{eventId}")
    public ResponseEntity isSurvey(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventServices.isSurvey(eventId));
    }
}






