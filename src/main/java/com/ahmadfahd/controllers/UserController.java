package com.ahmadfahd.controllers;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.enums.ROLES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    @GetMapping("/disabled")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity findAllDisable() {

        return ResponseEntity.ok(userServices.findAllDisable());
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

    @PostMapping("/create")
    public ResponseEntity addUser(@RequestBody @Valid UsersDTO usersDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
//        notificationService.addUserNotification(usersDTO);
            userServices.addUser(usersDTO, ROLES.ROLE_USER.toString());
            return ResponseEntity.ok().build();
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
    public ResponseEntity findByUser(@PathVariable String name) {
        if (userServices.isUser(name)) {
            return ResponseEntity.ok(userServices.findByUsernameAndEnabledTrue(name));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/organizers")
    public ResponseEntity findOrgs(){
        return ResponseEntity.ok(userServices.findAllOrganizers());
    }
}