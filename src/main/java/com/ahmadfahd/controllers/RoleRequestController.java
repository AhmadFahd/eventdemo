package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.RoleRequestService;
import com.ahmadfahd.enums.ROLES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request")
public class RoleRequestController {

    @Autowired
    private RoleRequestService roleRequestService;

    @GetMapping("/admins")
    public ResponseEntity adminRequests() {
        return ResponseEntity.ok(roleRequestService.getRoleRequests(ROLES.ROLE_ADMIN.toString()));
    }
    @GetMapping("/organizers")
    public ResponseEntity orgRequests() {
        return ResponseEntity.ok(roleRequestService.getRoleRequests(ROLES.ROLE_ORGANIZER.toString()));
    }
    @GetMapping("/all")
    public ResponseEntity getAllRequests() {
        return ResponseEntity.ok(roleRequestService.getAllRequests());
    }
    @GetMapping("/accept/{id}")
    public ResponseEntity acceptRequest(@PathVariable Long id) {
        roleRequestService.acceptRoleRequest(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity adminRequest(@PathVariable Long id) {
        roleRequestService.addRequest(id,ROLES.ROLE_ADMIN.toString());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/org/{id}")
    public ResponseEntity orgRequest(@PathVariable Long id) {
        roleRequestService.addRequest(id,ROLES.ROLE_ORGANIZER.toString());
        return ResponseEntity.ok().build();
    }
}
