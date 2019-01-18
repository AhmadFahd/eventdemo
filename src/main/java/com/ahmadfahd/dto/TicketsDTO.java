package com.ahmadfahd.dto;

public class TicketsDTO {
    private long id;
    private EventsDTO event;
    private UserGetDto user;
    private boolean chicked;

    public boolean isChicked() {
        return chicked;
    }

    public void setChicked(boolean chicked) {
        this.chicked = chicked;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public EventsDTO getEvent() { return event; }

    public void setEvent(EventsDTO event) { this.event = event; }

    public UserGetDto getUser() { return user; }

    public void setUser(UserGetDto user) { this.user = user; }
}
