package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.Services.RoleServices;
import com.ahmadfahd.ServicesImplementation.RoleServicesImp;
import com.ahmadfahd.entity.RolesEntity;
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

    @GetMapping("/AdminAccess/view")
    public ResponseEntity getAllRoles(){

        return ResponseEntity.ok(roleServices.getAllRoles());
    }

    @GetMapping("/view/{roleid}")
    public ResponseEntity findById(@PathVariable Long roleid){

        return ResponseEntity.ok(roleServices.findById(roleid));
    }

}