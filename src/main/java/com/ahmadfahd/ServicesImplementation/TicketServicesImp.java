package com.ahmadfahd.ServicesImplementation;


import com.ahmadfahd.Services.TicketServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.TicketsDTO;
import com.ahmadfahd.entity.*;
import com.ahmadfahd.enums.ACTIONS;
import com.ahmadfahd.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private RatingRepository ratingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FeedRepository feedRepository;

//    @Override
//    public List<TicketsDTO> getAllTickets() {
//        List<TicketsEntity> ticketsEntityList = ticketsRepository.findAll();
//        List<TicketsDTO> ticketsDTOList = ObjectMapperUtils.mapAll(ticketsEntityList, TicketsDTO.class);
//        return ticketsDTOList;
//    }
//
//    @Override
//    public TicketsDTO findById(Long ticketid) {
//        if (ticketsRepository.findById(ticketid).isPresent()) {
//            TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
//            TicketsDTO ticketsDTO = modelMapper.map(ticketsEntity, TicketsDTO.class);
//            return ticketsDTO;
//        }
//        return null;
//    }

    @Override
    public void addTicket(Long eventid, Long userid) {
        TicketsEntity ticketsEntity = new TicketsEntity();
        Optional<EventsEntity> eventsOptional = eventsRepository.findByIdAndDeletedFalseAndApprovedTrueAndDateAfterAndSurveyFalse(eventid, LocalDate.now());
        Optional<UsersEntity> usersOptional = usersRepository.findById(userid);
        if (eventsOptional.isPresent() && usersOptional.isPresent()) {
            EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
            UsersEntity usersEntity = usersRepository.findById(userid).get();
            LocalDate eventDate = eventsEntity.getDate();
            long counter = ticketsRepository.countByEventIdAndCanceledFalse(eventid);
            if(ticketsRepository.existsByUserIdAndEventIdAndCanceledFalse(userid, eventid))
            {
                throw new RuntimeException("You already have a ticket in this event!");
            }
            if(ticketsRepository.existsByUserIdAndEventDateAndCanceledFalse(userid, eventDate))
            {
                throw new RuntimeException("You have an event in this date!");
            }
            if (eventsEntity.getCapacity() > counter) {
                ticketsEntity.setEvent(eventsEntity);
                ticketsEntity.setUser(usersEntity);
                FeedEntity feedEntity = new FeedEntity();
                feedEntity.setUser(usersEntity);
                feedEntity.setAction(ACTIONS.BOOK.toString());
                feedEntity.setTime(LocalDateTime.now());
                ticketsRepository.save(ticketsEntity);
                feedEntity.setTargetEvent(eventsEntity);
                feedRepository.save(feedEntity);
            }
        }
    }
    @Override
    public long CountEventTickets(Long eventid) {
        if (eventsRepository.findById(eventid).isPresent()) {
            long count = ticketsRepository.countByEventIdAndCanceledFalse((eventid));
            return count;
        }
        return 0;
    }
    @Override
    public void ChickinTicket(String ticketid) {

        if (ticketsRepository.findById(ticketid).isPresent()) {
            TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
            ticketsEntity.setChicked(true);
            ticketsRepository.save(ticketsEntity);
        }
    }

    @Override
    public void CancelTicket(String ticketid) {
        if (ticketsRepository.findById(ticketid).isPresent()) {
            TicketsEntity ticketsEntity = ticketsRepository.findById(ticketid).get();
            ticketsEntity.setCanceled(true);
            ticketsRepository.save(ticketsEntity);
        }
    }

    @Override
    public List<TicketsDTO> findNonRatedByUser(Long uId) {
        List<TicketsEntity> ticketsEntities = ticketsRepository.findByUserIdAndChickedTrue(uId);
        List<TicketsEntity> filtered = new ArrayList<>();
        for (TicketsEntity ticketsEntity : ticketsEntities)
        {
            if (!ratingRepository.existsByUserIdAndEventId(uId,ticketsEntity.getEvent().getId())){
            filtered.add(ticketsEntity);}
        }
        if (filtered.isEmpty())
        {return null;}
        List<TicketsDTO> ticketsDTOList = ObjectMapperUtils.mapAll(filtered, TicketsDTO.class);
        return ticketsDTOList;
    }

    // TODO: 1/20/2019 merge with find non rated
    @Override
    public List<TicketsDTO> findNonAttended(Long uId) {
        List<TicketsEntity> ticketsEntities = ticketsRepository.findByUserIdAndCanceledFalse(uId);
        List<TicketsEntity> filtered = new ArrayList<>();
        for (TicketsEntity ticketsEntity : ticketsEntities)
        {
            if (!ratingRepository.existsByUserIdAndEventId(uId,ticketsEntity.getEvent().getId())){
                filtered.add(ticketsEntity);}
        }
        if (filtered.isEmpty())
        {return null;}
        List<TicketsDTO> ticketsDTOList = ObjectMapperUtils.mapAll(filtered, TicketsDTO.class);
        return ticketsDTOList;
    }

    @Override
    public List<TicketsDTO> findEventsTickets(Long eId){
        List<TicketsEntity> ticketsEntities = ticketsRepository.findByEventIdAndCanceledFalse(eId);
        List<TicketsDTO> ticketsDTOList = ObjectMapperUtils.mapAll(ticketsEntities,TicketsDTO.class);
        return ticketsDTOList;
    }
}
