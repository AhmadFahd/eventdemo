package com.ahmadfahd.controller.secureControllers;


import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/secure")
public class UserSecureController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/UsersAccess/view")
    public ResponseEntity findAllPresent() {
        return ResponseEntity.ok(userServices.findAllPresent()); }

    @GetMapping("/AdminAccess/view")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllUser() {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

}