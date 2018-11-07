package com.ahmadfahd.Services;

import com.ahmadfahd.entity.RatingDTO;
import com.ahmadfahd.entity.RatingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RatingServices {
    List<RatingEntity> getAllRatings();
    Optional<RatingEntity> findById(Long rateid);
    ResponseEntity addRating(RatingDTO ratingDTO, Long ticketId);
    ResponseEntity updateRating(RatingDTO ratingDTO, Long rateid);


}
