package com.ahmadfahd.Services;

import com.ahmadfahd.entity.RolesEntity;

import java.util.List;
import java.util.Optional;

public interface RoleServices {


    List<RolesEntity> getAllRoles();
    Optional<RolesEntity> findById(Long roleid) ;
    void addRole(RolesEntity rolesEntity);
    void updateRole(RolesEntity rolesEntity, String roleid) ;
    void deleteById(Long roleid);
}
