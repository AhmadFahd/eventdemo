package com.ahmadfahd.controllers;

import com.ahmadfahd.Services.RoleServices;
import com.ahmadfahd.ServicesImplementation.RoleServicesImp;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.enums.ROLES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleServices roleServices;

    @GetMapping("/view/{roleid}")
    public ResponseEntity findById(@PathVariable Long roleid) {
        return ResponseEntity.ok(roleServices.findById(roleid));
    }

    @GetMapping("/add/admin/{id}")
    public void giveAdminRole(@PathVariable Long id) {
        roleServices.giveRole(id, ROLES.ROLE_ADMIN.toString());
    }

    @GetMapping("/add/org/{id}")
    public void giveOrganizerRole(@PathVariable Long id) {
        roleServices.giveRole(id, ROLES.ROLE_ORGANIZER.toString());
    }
}