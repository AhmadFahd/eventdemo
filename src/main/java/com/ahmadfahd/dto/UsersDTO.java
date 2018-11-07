package com.ahmadfahd.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsersDTO {

//    private long userid;
    @NotNull(message = "Username is null")
//    @UniqueElements(message = "User-name is already exist")
    private String username;
    private String firstname;
    private String midname;
    private String lastname;
    private String email;
    private String userphone;
    private String password;
    private String usergender;
    private LocalDate userdob;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsergender() {
        return usergender;
    }

    public void setUsergender(String usergender) {
        this.usergender = usergender;
    }

    public LocalDate getUserdob() {
        return userdob;
    }

    public void setUserdob(LocalDate userdob) {
        this.userdob = userdob;
    }


    }

