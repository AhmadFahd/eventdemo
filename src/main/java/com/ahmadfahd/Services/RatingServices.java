package com.ahmadfahd.Services;

import com.ahmadfahd.dto.RatingDTO;
import com.ahmadfahd.entity.RatingEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RatingServices {
    ResponseEntity getAllRatings();
    ResponseEntity findById(Long rateid);
    ResponseEntity addRating(RatingDTO ratingDTO, Long ticketId);
    ResponseEntity updateRating(RatingDTO ratingDTO, Long rateid);


}
