package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.PasswordResetService;
import com.ahmadfahd.dto.PasswordResetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reset")
public class PasswordResetController {


    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/{username}")
    public ResponseEntity resetRequest(@PathVariable String username) {
        passwordResetService.passResetRequest(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send/{id}")
    public ResponseEntity resetPassword(@RequestBody PasswordResetDTO passwordResetDTO, @PathVariable String id) {
        passwordResetService.passReset(passwordResetDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check/{id}")
    public ResponseEntity isActive(@PathVariable String id) {
        return ResponseEntity.ok(passwordResetService.isActive(id));
    }
}