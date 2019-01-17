package com.ahmadfahd.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "RATING")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventsEntity event;
    private int rate;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public UsersEntity getUser() { return user; }

    public void setUser(UsersEntity user) { this.user = user; }

    public EventsEntity getEvent() { return event; }

    public void setEvent(EventsEntity event) { this.event = event; }

    public int getRate() { return rate; }

    public void setRate(int rate) { this.rate = rate; }
}