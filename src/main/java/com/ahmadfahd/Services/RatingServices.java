package com.ahmadfahd.Services;

import com.ahmadfahd.dto.RatingDTO;
import java.util.List;


public interface RatingServices {
    List<RatingDTO> getAllRatings();
    RatingDTO findById(Long rateid);
    void addRating(int rate, Long uId, Long eId);
    float findRateAvg(Long uid);

}
