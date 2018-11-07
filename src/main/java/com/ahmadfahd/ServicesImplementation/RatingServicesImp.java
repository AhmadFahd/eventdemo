package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.RatingServices;
import com.ahmadfahd.entity.RatingDTO;
import com.ahmadfahd.entity.RatingEntity;
import com.ahmadfahd.entity.TicketsEntity;
import com.ahmadfahd.repository.RatingRepository;
import com.ahmadfahd.repository.TicketsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServicesImp implements RatingServices {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RatingEntity> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<RatingEntity>  findById(Long rateid) {

        return ratingRepository.findById(rateid);
    }

    @Override
    public ResponseEntity addRating(RatingDTO ratingDTO, Long ticketId) {

        TicketsEntity ticketsEntity = ticketsRepository.findById(ticketId).get();
        if (ticketsEntity.isChicked())
        {
            RatingEntity ratingEntity = modelMapper.map(ratingDTO,RatingEntity.class);

            ratingEntity.setTicket(ticketsEntity);
            return ResponseEntity.ok(ratingRepository.save(ratingEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity updateRating(RatingDTO ratingDTO, Long rateid) {
        // FIXME: 11/7/2018
        if (ratingRepository.findById(rateid).isPresent()) {
            RatingEntity ratingEntity = modelMapper.map(ratingDTO, RatingEntity.class);
            ratingEntity.setRateid(ratingEntity.getRateid());
            ratingEntity.setTicket(ratingEntity.getTicket());
            return ResponseEntity.ok(ratingRepository.save(ratingEntity));
        }
        return ResponseEntity.badRequest().build();
    }
}