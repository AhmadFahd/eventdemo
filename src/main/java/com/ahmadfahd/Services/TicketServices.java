package com.ahmadfahd.Services;

import com.ahmadfahd.dto.TicketsDTO;
import com.ahmadfahd.entity.TicketsEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketServices {


    void addTicket(Long eventid, Long userid);
    long CountEventTickets(Long eventid);
//    List<TicketsDTO> findMyTickets(Long userid);
    void ChickinTicket(String ticketid);
    void CancelTicket(String ticketid);
    List<TicketsDTO> findNonRatedByUser(Long uId);
    List<TicketsDTO> findNonAttended(Long uId);
    List<TicketsDTO> findEventsTickets(Long eId);


    }