package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.RatingServices;
import com.ahmadfahd.entity.RatingEntity;
import com.ahmadfahd.entity.TicketsEntity;
import com.ahmadfahd.repository.RatingRepository;
import com.ahmadfahd.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServicesImp implements RatingServices {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private TicketsRepository ticketsRepository;

    @Override
    public List<RatingEntity> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public RatingEntity findById(Long rateid) {
        return ratingRepository.findById(rateid).get();
    }

    @Override
    public void addRating(RatingEntity ratingEntity,Long ticketId) {

        TicketsEntity ticketsEntity = ticketsRepository.findById(ticketId).get();
        if (ticketsEntity.isChicked())
        {
            ratingEntity.setTicket(ticketsEntity);
            ratingRepository.save(ratingEntity);
        }
    }

    @Override
    public void updateRating(RatingEntity ratingEntity, String rateid) {
        ratingRepository.save(ratingEntity);
    }
}