package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.RatingServices;
import com.ahmadfahd.dto.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Map;


@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingServices ratingServices;

    @GetMapping("/AdminAccess/view")
    public ResponseEntity getAllRatings(){
        return ResponseEntity.ok(ratingServices.getAllRatings());
    }

    @GetMapping("/view/{rateid}")
    public ResponseEntity findById(@PathVariable Long rateid) {
        return ResponseEntity.ok(ratingServices.findById(rateid));
    }

    // FIXME: 12/26/2018 
    // TODO: 12/26/2018  
    @GetMapping("/add/{rate}/{uId}/{eId}")
    public ResponseEntity addRating (@PathVariable  Long rate, @PathVariable  Long uId,@PathVariable  Long eId) {
        ratingServices.addRating(Math.toIntExact(rate),uId,eId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/avg/{uid}")
    public String findRateAvg(@PathVariable Long uid) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        double avg = ratingServices.findRateAvg(uid);
        return String.valueOf(df.format(avg));
    }


}