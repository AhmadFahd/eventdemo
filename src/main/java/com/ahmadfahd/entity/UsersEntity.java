package com.ahmadfahd.entity;

import com.ahmadfahd.security.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="USERS")
public class UsersEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    //    @UniqueElements
    private String username;
//    private String firstname;
//    private String midname;
//    private String lastname;
//    private String email;
//    private String userphone;
    private String password;
    @ManyToOne
    private RolesEntity rolesEntity;
//    private String usergender;
//    private LocalDate userdob;
//    @JsonIgnore
//    @ColumnDefault(value = "0")
    private boolean deleted;

}