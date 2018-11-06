package com.ahmadfahd.ServicesImplementation;


import com.ahmadfahd.Services.EventServices;
import com.ahmadfahd.Services.TicketServices;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.TicketsEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.TicketsRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServicesImp implements TicketServices {

    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public List<TicketsEntity> getAllTickets() {
        return ticketsRepository.findAll();
    }

    @Override
    public TicketsEntity findById(Long ticketid) {
        return ticketsRepository.findById(ticketid).get();
    }

    @Override
    public ResponseEntity addTicket(TicketsEntity ticketsEntity, Long eventid, Long userid) {
        Optional<EventsEntity> eventsOptional = eventsRepository.findByEventidAndDeletedFalseAndApprovedTrueAndEventdateAfter(eventid,LocalDate.now());
        Optional<UsersEntity> usersOptional = usersRepository.findById(userid);
        if (eventsOptional.isPresent()&&usersOptional.isPresent()) {
            EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
            UsersEntity usersEntity = usersRepository.findById(userid).get();
            LocalDate eventDate = eventsEntity.getEventdate();
            long counter = ticketsRepository.countByEventidAndCanceledFalse(eventsEntity);
            long overlap = ticketsRepository.countByUseridAndDate(usersEntity, eventDate);
            if (eventsEntity.getEventcapacity() > counter && overlap == 0) {
                ticketsEntity.setEventid(eventsEntity);
                ticketsEntity.setUserid(usersEntity);
                ticketsEntity.setDate(eventsEntity.getEventdate());
                ticketsRepository.save(ticketsEntity);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }


    @Override
    public void updateTicket(TicketsEntity ticketsEntity, Long ticketid) {
        ticketsRepository.save(findById(ticketid));
    }

    @Override
    public Long CountEventTickets(Long eventid) {

        return ticketsRepository.countByEventidAndCanceledFalse(eventsRepository.findById(eventid).get());
    }
    @Override
    public List<TicketsEntity> findMyTickets(Long userid) {

        return ticketsRepository.findByUserid(usersRepository.findById(userid).get());
    }
    @Override
    public void ChickinTicket (Long ticketid){

        TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
        ticketsEntity.setChicked(true);
        ticketsRepository.save(ticketsEntity);
    }

    @Override
    public void CancelTicket (Long ticketid){
        TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
        ticketsEntity.setCanceled(true);
        ticketsRepository.save(ticketsEntity);
    }


}




