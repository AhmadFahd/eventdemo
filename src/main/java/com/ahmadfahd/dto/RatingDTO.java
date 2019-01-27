package com.ahmadfahd.dto;

import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.UsersEntity;

import javax.validation.constraints.Size;

public class RatingDTO {
    // FIXME: 12/22/2018
    private long id;
    @Size(min = 1, max = 5, message = "please rate 1-5")
    private int rate;
    private UserGetDto user;
    private EventsDTO event;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public UserGetDto getUser() {
        return user;
    }

    public void setUser(UserGetDto user) {
        this.user = user;
    }

    public EventsDTO getEvent() {
        return event;
    }

    public void setEvent(EventsDTO event) {
        this.event = event;
    }
}