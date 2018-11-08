package com.ahmadfahd.Services;

import com.ahmadfahd.entity.TicketsEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketServices {

    List<TicketsEntity> getAllTickets();
    TicketsEntity findById(Long ticketid);
    ResponseEntity addTicket(Long eventid, Long userid);
    Long CountEventTickets(Long eventid);
    List<TicketsEntity> findMyTickets(Long userid);
    void ChickinTicket(Long ticketid);
    void CancelTicket(Long ticketid);


}