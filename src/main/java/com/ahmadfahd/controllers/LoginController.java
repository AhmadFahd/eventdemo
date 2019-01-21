package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.LoginBody;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserServices userServices;

    // FIXME: 1/21/2019 old Login
//    @GetMapping("/UserData")
//    public ResponseEntity login(Principal principal) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        Map<String, Object> map = new HashMap<>();
//        map.put("userAuth", userDetails.getAuthorities());
//        map.put("userId", userServices.findByUsername(principal.getName()).getId());
//        return ResponseEntity.ok(map);
//    }
// // FIXME: 1/21/2019 This function solved login pop up message
    @PostMapping("/UserData")
    public ResponseEntity login(@RequestBody LoginBody loginBody) {
        if (!userServices.isActiveUser(loginBody.getUsername())) {
            return ResponseEntity.badRequest().body(new RuntimeException("User not found"));
        }
        UsersDTO usersDTO = userServices.findByUsername(loginBody.getUsername());
//        To Check The Case sensitive ,, but it's not required
//        if (!usersDTO.getUsername().equals(loginBody.getUsername())) {
//            return ResponseEntity.badRequest().body(new RuntimeException("UserCaseSensitive"));
//        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginBody.getUsername());
        if (new BCryptPasswordEncoder().matches(loginBody.getPassword(), usersDTO.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("userAuth", userDetails.getAuthorities());
            map.put("userId", usersDTO.getId());
            return ResponseEntity.ok(map);
        }
        return ResponseEntity.badRequest().body(new RuntimeException("Password Incorrect!"));
    }
}
