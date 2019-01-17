package com.ahmadfahd.dto;

import com.ahmadfahd.entity.CommentsEntity;
import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.RatingEntity;
import com.ahmadfahd.entity.UsersEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class FeedsDTO {
    private Long id;
    private UserGetDto user;
    private String action;
    private EventsDTO targetEvent;
    private CommentsDTO targetComment;
    private UserGetDto targetUser;
    private RatingDTO targetRate;
    private LocalDateTime time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserGetDto getUser() {
        return user;
    }

    public void setUser(UserGetDto user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public EventsDTO getTargetEvent() {
        return targetEvent;
    }

    public void setTargetEvent(EventsDTO targetEvent) {
        this.targetEvent = targetEvent;
    }

    public CommentsDTO getTargetComment() {
        return targetComment;
    }

    public void setTargetComment(CommentsDTO targetComment) {
        this.targetComment = targetComment;
    }

    public UserGetDto getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UserGetDto targetUser) {
        this.targetUser = targetUser;
    }

    public RatingDTO getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(RatingDTO targetRate) {
        this.targetRate = targetRate;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}