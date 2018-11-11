package com.ahmadfahd.Services;

import com.ahmadfahd.dto.TicketsDTO;
import com.ahmadfahd.entity.TicketsEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketServices {

    List<TicketsDTO>  getAllTickets();
    ResponseEntity findById(Long ticketid);
    ResponseEntity addTicket(Long eventid, Long userid);
    ResponseEntity CountEventTickets(Long eventid);
    ResponseEntity findMyTickets(Long userid);
    ResponseEntity ChickinTicket(Long ticketid);
    ResponseEntity CancelTicket(Long ticketid);


}