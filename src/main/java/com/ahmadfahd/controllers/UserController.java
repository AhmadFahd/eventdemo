package com.ahmadfahd.controllers;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.enums.ROLES;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/present")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity findAllPresent() {

        return ResponseEntity.ok(userServices.findAllPresent());
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getAllUser() {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @GetMapping("/view/{userid}")
    public ResponseEntity findById(@PathVariable Long userid) {
        if (userServices.findById(userid) != null) {
            return ResponseEntity.ok(userServices.findById(userid));
        } return ResponseEntity.badRequest().body("No user");
    }
    @PostMapping("/admin/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity addAdmin(@RequestBody @Valid UsersDTO usersDTO, BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
//        notificationService.addUserNotification(usersDTO);
        userServices.addUser(usersDTO,ROLES.ROLE_ADMIN.toString());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/organizer/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity addOrganizer(@RequestBody @Valid UsersDTO usersDTO, BindingResult result) {
            if (result.hasErrors()){
                return ResponseEntity.badRequest().body(result.getAllErrors());
            }
//        notificationService.addUserNotification(usersDTO);
        userServices.addUser(usersDTO,ROLES.ROLE_ORGANIZER.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity addUser(@RequestBody @Valid UsersDTO usersDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
//        notificationService.addUserNotification(usersDTO);
            userServices.addUser(usersDTO, ROLES.ROLE_USER.toString());
            return ResponseEntity.created(null).build();
    }


    @PutMapping("/edit/{userid}")
    public ResponseEntity updateUser(@RequestBody @Valid UsersDTO usersDTO, @PathVariable Long userid, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        userServices.updateUser(usersDTO, userid);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/delete/{userid}")
    public ResponseEntity deleteById(@PathVariable Long userid) {
        userServices.enableUser(userid,false);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/enable/{userid}")
    public ResponseEntity enableById(@PathVariable Long userid) {
        userServices.enableUser(userid,true);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity findByUser(@PathVariable String name){
        return ResponseEntity.ok(userServices.findByUsername(name));
    }
    @GetMapping("/organizers")
    public ResponseEntity findOrgs(){
        return ResponseEntity.ok(userServices.findAllOrganizers());
    }
}