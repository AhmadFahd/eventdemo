package com.ahmadfahd.dto;

import java.time.LocalDateTime;

public class CommentsDTO {
    private EventsDTO event;
    private String comment;
    private UsersDTO user;
    private LocalDateTime time;

    public EventsDTO getEvent() {
        return event;
    }

    public void setEvent(EventsDTO event) {
        this.event = event;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UsersDTO getUser() { return user; }

    public void setUser(UsersDTO user) { this.user = user; }

    public LocalDateTime getTime() { return time; }

    public void setTime(LocalDateTime time) { this.time = time; }
}