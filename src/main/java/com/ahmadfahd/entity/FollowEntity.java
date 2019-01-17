package com.ahmadfahd.entity;

import javax.persistence.*;

@Entity
@Table(name = "FOLLOW")
public class FollowEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower",referencedColumnName = "id")
    private UsersEntity user;
    @ManyToOne
    @JoinColumn(name = "followed",referencedColumnName = "id")
    private UsersEntity followed;
    private boolean status;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public UsersEntity getUser() { return user; }

    public void setUser(UsersEntity user) { this.user = user; }

    public UsersEntity getFollowed() { return followed; }

    public void setFollowed(UsersEntity followed) { this.followed = followed; }

    public boolean isStatus() { return status; }

    public void setStatus(boolean status) { this.status = status; }
}
