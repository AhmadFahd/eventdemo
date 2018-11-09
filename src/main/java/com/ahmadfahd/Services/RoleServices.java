package com.ahmadfahd.Services;

import com.ahmadfahd.entity.RolesEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RoleServices {


    ResponseEntity getAllRoles();
    ResponseEntity findById(Long roleid) ;
}
