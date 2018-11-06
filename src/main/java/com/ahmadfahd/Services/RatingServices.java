package com.ahmadfahd.Services;

import com.ahmadfahd.entity.RatingEntity;

import java.util.List;

public interface RatingServices {
    List<RatingEntity> getAllRatings();
    RatingEntity findById(Long rateid);
    void addRating(RatingEntity ratingEntity,Long ticketId);
    void updateRating(RatingEntity ratingEntity, String rateid);


}
