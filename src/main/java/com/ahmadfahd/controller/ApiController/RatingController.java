package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.Services.RatingServices;
import com.ahmadfahd.entity.RatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingServices ratingServices;

    @GetMapping("/AdminAccess/view")
    public List<RatingEntity> getAllRatings(){
        return ratingServices.getAllRatings(); }

    @GetMapping("/view/{rateid}")
    public RatingEntity findById(@PathVariable Long rateid) {
        return ratingServices.findById(rateid); }

    @PostMapping("/add/{ticketid}")
    public void addRating (@RequestBody RatingEntity ratingEntity,@PathVariable  Long ticketid){
        ratingServices.addRating(ratingEntity,ticketid);
    }
    @PutMapping("/edit/{rateid}")
    public void updateRating(@RequestBody RatingEntity ratingEntity, @PathVariable String rateid) {
        ratingServices.updateRating(ratingEntity, rateid); }

}