package com.ahmadfahd.dto;

import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.UsersEntity;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class TicketsDTO {
    // FIXME: 11/9/2018
    //needs some logic
    private long ticketid;
    private EventsEntity eventid;
    private UsersEntity userid;

    public long getTicketid() { return ticketid; }

    public void setTicketid(long ticketid) { this.ticketid = ticketid; }

    public EventsEntity getEventid() { return eventid; }

    public void setEventid(EventsEntity eventid) { this.eventid = eventid; }

    public UsersEntity getUserid() { return userid; }

    public void setUserid(UsersEntity userid) { this.userid = userid; }
}
