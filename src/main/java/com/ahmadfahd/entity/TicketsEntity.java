package com.ahmadfahd.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="TICKET")
public class TicketsEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventsEntity event;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;
    @ColumnDefault("0")
    private boolean chicked;
    @ColumnDefault("0")
    private boolean canceled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventsEntity getEvent() {
        return event;
    }

    public void setEvent(EventsEntity event) {
        this.event = event;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public boolean isChicked() {
        return chicked;
    }

    public void setChicked(boolean chicked) {
        this.chicked = chicked;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

}