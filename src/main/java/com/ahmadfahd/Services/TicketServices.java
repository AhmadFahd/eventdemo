package com.ahmadfahd.Services;

import com.ahmadfahd.dto.TicketsDTO;
import com.ahmadfahd.entity.TicketsEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketServices {

//    List<TicketsDTO>  getAllTickets();
//    TicketsDTO findById(Long ticketid);
    void addTicket(Long eventid, Long userid);
    long CountEventTickets(Long eventid);
//    List<TicketsDTO> findMyTickets(Long userid);
    void ChickinTicket(Long ticketid);
    void CancelTicket(Long ticketid);
    List<TicketsDTO> findNonRatedByUser(Long uId);
    List<TicketsDTO> findNonAttended(Long uId);
    List<TicketsDTO> findEventsTickets(Long eId);


    }