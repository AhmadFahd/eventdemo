package com.ahmadfahd.ServicesImplementation;


import com.ahmadfahd.Services.EventServices;
import com.ahmadfahd.Services.TicketServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.TicketsDTO;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.TicketsEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.TicketsRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TicketsDTO> getAllTickets() {
        List<TicketsEntity> ticketsEntityList = ticketsRepository.findAll();
        List<TicketsDTO> ticketsDTOList = ObjectMapperUtils.mapAll(ticketsEntityList, TicketsDTO.class);
        return ticketsDTOList; //ResponseEntity.ok(ticketsDTOList);

    }

    @Override
    public ResponseEntity findById(Long ticketid) {
        if (ticketsRepository.findById(ticketid).isPresent()) {
            TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
            TicketsDTO ticketsDTO = modelMapper.map(ticketsEntity, TicketsDTO.class);
            return ResponseEntity.ok(ticketsDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity addTicket(Long eventid, Long userid) {
        TicketsEntity ticketsEntity = new TicketsEntity();
        Optional<EventsEntity> eventsOptional = eventsRepository.findByEventidAndDeletedFalseAndApprovedTrueAndEventdateAfter(eventid, LocalDate.now());
        Optional<UsersEntity> usersOptional = usersRepository.findById(userid);
        if (eventsOptional.isPresent() && usersOptional.isPresent()) {
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
    public ResponseEntity CountEventTickets(Long eventid) {
        if (eventsRepository.findById(eventid).isPresent()) {
            long count = ticketsRepository.countByEventidAndCanceledFalse(eventsRepository.findById(eventid).get());
            return ResponseEntity.ok(count);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity findMyTickets(Long userid) {
        if (!ticketsRepository.findByUserid(usersRepository.findById(userid).get()).isEmpty()) {
            List<TicketsEntity> ticketsEntityList = ticketsRepository.findByUserid(usersRepository.findById(userid).get());
            List<TicketsDTO> ticketsDTOList = ObjectMapperUtils.mapAll(ticketsEntityList, TicketsDTO.class);
            return ResponseEntity.ok(ticketsDTOList);
        } return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity ChickinTicket(Long ticketid) {
        if (ticketsRepository.findById(ticketid).isPresent()) {
            TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
            ticketsEntity.setChicked(true);
            ticketsRepository.save(ticketsEntity);
            TicketsDTO ticketsDTO = modelMapper.map(ticketsEntity, TicketsDTO.class);
            return ResponseEntity.ok(ticketsDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity CancelTicket(Long ticketid) {
        if (ticketsRepository.findById(ticketid).isPresent()) {
            TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
            ticketsEntity.setCanceled(true);
            ticketsRepository.save(ticketsEntity);
            TicketsDTO ticketsDTO = modelMapper.map(ticketsEntity, TicketsDTO.class);
            return ResponseEntity.ok(ticketsDTO);
        }
        return ResponseEntity.noContent().build();
    }
}
