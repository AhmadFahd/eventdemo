package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.EventServices;
import com.ahmadfahd.dto.EventsDTO;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.TicketsEntity;
import com.ahmadfahd.enums.ACTIONS;
import com.ahmadfahd.repository.EventsRepository;
import com.ahmadfahd.repository.FeedRepository;
import com.ahmadfahd.repository.TicketsRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventServicesImp implements EventServices {

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private FeedRepository feedRepository;


    @Override
    public List<EventsDTO> getAllEvents() {
        List<EventsEntity> eventsEntityList = eventsRepository.findAll();
        List<EventsDTO> eventsDTO = ObjectMapperUtils.mapAll(eventsEntityList, EventsDTO.class);
        return eventsDTO;
    }

    @Override
    public EventsDTO findById(Long eventid) {
        if (eventsRepository.findByIdAndDeletedFalseAndApprovedTrueAndDateAfter(eventid, LocalDate.now()).isPresent()) {
            EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
            EventsDTO eventsDTO = modelMapper.map(eventsEntity, EventsDTO.class);
            return eventsDTO;
        }
        return null;
    }

    @Override
    public void addEvent(EventsDTO eventsDTO, Long userid) {

        if (eventsDTO.getDate().isAfter(LocalDate.now().minusDays(1))) {
            EventsEntity eventsEntity;
            eventsEntity = modelMapper.map(eventsDTO, EventsEntity.class);
            eventsEntity.setOrganizer(usersRepository.findById(userid).get());
            eventsRepository.save(eventsEntity);
        }
    }

    @Override
    public void updateEvent(EventsDTO eventsDTO, Long eventid) {
        EventsEntity eventsEntity1 = eventsRepository.findById(eventid).get();
        EventsEntity eventsEntity;
        eventsEntity = modelMapper.map(eventsDTO, EventsEntity.class);
        eventsEntity.setId(eventsEntity1.getId());
        eventsEntity.setOrganizer(eventsEntity1.getOrganizer());
        eventsRepository.save(eventsEntity);

    }

    @Override
    public void deleteById(Long eventid) {
        EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
        List<TicketsEntity> ticketsEntities = ticketsRepository.findByEventIdAndCanceledFalse(eventid);
        for (TicketsEntity ticketsEntity: ticketsEntities){
            ticketsEntity.setCanceled(true);
            ticketsRepository.save(ticketsEntity);
        }
        eventsEntity.setDeleted(true);
        eventsRepository.save(eventsEntity);
    }

    @Override
    public void eventApprove(Long eventid, boolean aproved) {
        EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
        eventsEntity.setApproved(aproved);
        eventsRepository.save(eventsEntity);
        boolean feed = feedRepository.existsByTargetEventIdAndAction(eventid, ACTIONS.ADD_EVENT.toString());
        if (aproved && !feed) {
            FeedEntity feedEntity = new FeedEntity();
            feedEntity.setUser(usersRepository.findById(eventsEntity.getOrganizer().getId()).get());
            feedEntity.setAction(ACTIONS.ADD_EVENT.toString());
            feedEntity.setTime(LocalDateTime.now());
            feedEntity.setTargetEvent(eventsEntity);
            feedRepository.save(feedEntity);
        }
    }

//    @Override
//    public List<EventsDTO> findByCity(String eventcity) {
//            List<EventsEntity> eventsEntityList = eventsRepository.findAllByEventcity(eventcity);
//            List<EventsDTO> eventsDTOList = ObjectMapperUtils.mapAll(eventsEntityList, EventsDTO.class);
//            return eventsDTOList;
//        }

//    @Override
//    public List<EventsDTO> findByDate(LocalDate eventdate) {
//            List<EventsEntity> eventsEntityList = eventsRepository.findByEventdate(eventdate);
//            List<EventsDTO> eventsDTOList = ObjectMapperUtils.mapAll(eventsEntityList, EventsDTO.class);
//            return eventsDTOList;
//    }


    @Override
    public List<EventsDTO> getAllActive() {
        LocalDate localDate = LocalDate.now();
        if (!eventsRepository.findByDeletedFalseAndApprovedTrueAndDateAfter(localDate).isEmpty()) {
            List<EventsEntity> eventsEntityList = eventsRepository.findByDeletedFalseAndApprovedTrueAndDateAfter(localDate);
            List<EventsDTO> eventsDTOList = ObjectMapperUtils.mapAll(eventsEntityList, EventsDTO.class);
            return eventsDTOList;
        }
        return null;
    }

    @Override
    public List<EventsDTO> getNonApproved(Long id) {
        List<EventsEntity> eventsEntityList = eventsRepository.findByOrganizerIdAndDeletedFalseAndApprovedFalse(id);
        if (!eventsEntityList.isEmpty()) {
            List<EventsDTO> eventsDTOList = ObjectMapperUtils.mapAll(eventsEntityList, EventsDTO.class);
            return eventsDTOList;
        }
        return null;
    }

    @Override
    public List<EventsDTO> getAllNonApproved() {
        List<EventsEntity> eventsEntityList = eventsRepository.findByDeletedFalseAndApprovedFalse();
        if (!eventsEntityList.isEmpty()) {
            List<EventsDTO> eventsDTOList = ObjectMapperUtils.mapAll(eventsEntityList, EventsDTO.class);
            return eventsDTOList;
        }
        return null;
    }


//    @Override
//    public EventsDTO findIfPresent(Long eventid) {
//        LocalDate date = LocalDate.now();
//        if (eventsRepository.findByEventidAndDeletedFalseAndApprovedTrueAndEventdateAfter(eventid, date).isPresent()) {
//            EventsEntity eventsEntity = eventsRepository.findById(eventid).get();
//            EventsDTO eventsDTO = modelMapper.map(eventsEntity, EventsDTO.class);
//            return eventsDTO;
//        }
//        return null;
//    }

    @Override
    public Map getOrganizer(long eventid) {
        Map<String, String> map = new HashMap<>();
        map.put("OrganizerId", eventsRepository.findById(eventid).get().getOrganizer().getId().toString());
        map.put("OrganizerName", eventsRepository.findById(eventid).get().getOrganizer().getUsername());
        return map;
    }


    @Override
    public List<EventsDTO> findByUser(Long id) {
        return ObjectMapperUtils.mapAll(eventsRepository.findByOrganizerIdAndDeletedFalseAndApprovedTrueAndDateAfter(id,LocalDate.now()), EventsDTO.class);
    }
}