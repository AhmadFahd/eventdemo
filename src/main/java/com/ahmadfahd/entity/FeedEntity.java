package com.ahmadfahd.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Table(name = "FEED")
public class FeedEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private UsersEntity user;
    private String action;
    @ManyToOne
    @JoinColumn(name = "eventid",referencedColumnName = "id")
    private EventsEntity targetEvent;
    @ManyToOne
    @JoinColumn(name = "comment",referencedColumnName = "id")
    private CommentsEntity targetComment;
    @ManyToOne
    @JoinColumn(name = "target_user",referencedColumnName = "id")
    private UsersEntity targetUser;
    @ManyToOne
    @JoinColumn(name = "target_rate",referencedColumnName = "id")
    private RatingEntity targetRate;
    private LocalDateTime time;

    public RatingEntity getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(RatingEntity targetRate) {
        this.targetRate = targetRate;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public UsersEntity getUser() { return user; }

    public void setUser(UsersEntity user) { this.user = user; }

    public String getAction() { return action; }

    public void setAction(String action) { this.action = action; }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public EventsEntity getTargetEvent() {
        return targetEvent;
    }

    public void setTargetEvent(EventsEntity targetEvent) {
        this.targetEvent = targetEvent;
    }

    public CommentsEntity getTargetComment() {
        return targetComment;
    }

    public void setTargetComment(CommentsEntity targetComment) {
        this.targetComment = targetComment;
    }

    public UsersEntity getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(UsersEntity targetUser) {
        this.targetUser = targetUser;
    }
}
