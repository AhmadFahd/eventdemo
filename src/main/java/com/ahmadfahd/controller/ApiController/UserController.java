package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/UsersAccess/view")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity findAllPresent(Principal principal) {
        return ResponseEntity.ok(userServices.findAllPresent());
    }

    @GetMapping("/AdminAccess/view")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getAllUser(Principal principal) {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @GetMapping("/view/{userid}")
    public ResponseEntity findById(@PathVariable Long userid) {
        return ResponseEntity.ok(userServices.findById(userid)); }

    @PostMapping("/addAdmin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity addAdmin(@RequestBody @Valid UsersDTO usersDTO, BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        notificationService.addUserNotification(usersDTO);
        return ResponseEntity.ok(userServices.addUser(usersDTO,"ADMIN")); }


    @PostMapping("/addOrganizer")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity addOrganizer(@RequestBody @Valid UsersDTO usersDTO, BindingResult result) {
            if (result.hasErrors()){
                return ResponseEntity.badRequest().body(result.getAllErrors());
            }
        notificationService.addUserNotification(usersDTO);
        return ResponseEntity.ok(userServices.addUser(usersDTO,"ORGANIZER"));
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity addUser(@RequestBody @Valid UsersDTO usersDTO, BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        notificationService.addUserNotification(usersDTO);
        return ResponseEntity.ok(userServices.addUser(usersDTO, "USER"));
    }


    @PutMapping("/edit/{userid}")
    public ResponseEntity updateUser(@RequestBody @Valid UsersDTO usersDTO, @PathVariable Long userid, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(userServices.updateUser(usersDTO, userid));

    }

    @PutMapping("/delete/{userid}")
    public ResponseEntity deleteById(@PathVariable Long userid) {
        return ResponseEntity.ok(userServices.deleteUser(userid));
    }
}