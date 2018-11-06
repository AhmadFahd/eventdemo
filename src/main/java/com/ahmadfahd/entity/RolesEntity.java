package com.ahmadfahd.entity;

import javax.persistence.*;

@Entity
@Table(name="ROLES")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleid;
    private String roletype;

    public long getRoleid() { return roleid; }

    public void setRoleid(long roleid) { this.roleid = roleid; }

    public String getRoletype() { return roletype; }

    public void setRoletype(String roletype) { this.roletype = roletype; }
}