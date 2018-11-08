package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.TicketServices;

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

    @GetMapping("/AdminAccess/view")
    public ResponseEntity getAllTickets() {
        if (ticketServices.getAllTickets().isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ticketServices.getAllTickets());
    }


    @GetMapping("/view/{ticketid}")
    public TicketsEntity findById(@PathVariable Long ticketid) {
        return ticketServices.findById(ticketid);
    }


    @PostMapping("/add/{eventid}/{userid}")
    public void addTicket(@PathVariable Long eventid, @PathVariable Long userid) {
        ticketServices.addTicket(eventid , userid);
        notificationService.bookTicketNotification(eventid,userid);

    }
    @GetMapping("/count/{eventid}")
    public Long CountEventTickets(@PathVariable String eventid) {

        return ticketServices.CountEventTickets(Long.valueOf(eventid));

    }

    @GetMapping("/{userid}")
    public List<TicketsEntity> findMyTickets(@PathVariable Long userid) {
        return ticketServices.findMyTickets(userid);
    }

    @GetMapping("/chickin/{ticketid}")
    public void ChickinTicket(@PathVariable Long ticketid) {
    ticketServices.ChickinTicket(ticketid);
    }

    @GetMapping("/cancel/{ticketid}")
    public void CancelTicket(@PathVariable Long ticketid) {
        ticketServices.CancelTicket(ticketid);
    }
}