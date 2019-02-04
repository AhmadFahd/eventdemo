package com.ahmadfahd.entity;


import javax.persistence.*;

@Entity
@Table(name = "Vote")
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventsEntity event;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public UsersEntity getUser() { return user; }

    public void setUser(UsersEntity user) { this.user = user; }

    public EventsEntity getEvent() { return event; }

    public void setEvent(EventsEntity event) { this.event = event; }
}
