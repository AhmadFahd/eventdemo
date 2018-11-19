package com.ahmadfahd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="ROLES")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleid;
    private String rolename;
    @ManyToOne
    @JoinColumn(name = "usermame",referencedColumnName = "usermame")
    private UsersEntity user;

    public long getRoleid() { return roleid; }

    public void setRoleid(long roleid) { this.roleid = roleid; }

    public String getRolename() { return rolename; }

    public void setRolename(String rolename) { this.rolename = rolename; }

    public UsersEntity getUser() { return user; }

    public void setUser(UsersEntity user) { this.user = user; }
}