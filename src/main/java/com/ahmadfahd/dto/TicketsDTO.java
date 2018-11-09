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

}
