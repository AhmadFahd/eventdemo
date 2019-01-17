package com.ahmadfahd.entity;

import javax.persistence.*;


@Entity
@Table(name="ROLES")
public class RolesEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username")
    private UsersEntity user;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getRoleName() { return roleName; }

    public void setRoleName(String roleName) { this.roleName = roleName; }

    public UsersEntity getUser() { return user; }

    public void setUser(UsersEntity user) { this.user = user; }
}