package com.ahmadfahd.controllers;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.TicketServices;

import com.ahmadfahd.dto.TicketsDTO;
import com.ahmadfahd.entity.TicketsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {


    @Autowired
    private TicketServices ticketServices;
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/add/{eventid}/{userid}")
    public void addTicket(@PathVariable Long eventid, @PathVariable Long userid) {
//        notificationService.bookTicketNotification(eventid,userid);
        ticketServices.addTicket(eventid, userid);
    }
    @GetMapping("/count/{eventid}")
    public ResponseEntity CountEventTickets(@PathVariable Long eventid) {
        return ResponseEntity.ok(ticketServices.CountEventTickets(eventid));
    }

    @GetMapping("mytickets/{userid}")
    public ResponseEntity findMyTickets(@PathVariable Long userid) {

        return ResponseEntity.ok(ticketServices.findNonAttended(userid));
    }

    @GetMapping("/chickin/{ticketid}")
    public ResponseEntity ChickinTicket(@PathVariable String ticketid) {
        ticketServices.ChickinTicket(ticketid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cancel/{ticketid}")
    public ResponseEntity CancelTicket(@PathVariable String ticketid) {
        ticketServices.CancelTicket(ticketid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("nonrated/{userid}")
    public ResponseEntity findNonRated(@PathVariable Long userid) {

        return ResponseEntity.ok(ticketServices.findNonRatedByUser(userid));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity findEventTickets(@PathVariable Long id) {
        return ResponseEntity.ok(ticketServices.findEventsTickets(id));
    }


}