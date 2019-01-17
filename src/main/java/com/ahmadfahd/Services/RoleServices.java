package com.ahmadfahd.Services;

import com.ahmadfahd.dto.RolesDTO;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.enums.ROLES;

import java.util.List;

public interface RoleServices {


    List<RolesDTO> getAllRoles();

    RolesDTO  findById(Long roleid);

    List<RolesDTO> findByUser(UsersDTO usersDTO);
    void giveRole(Long id, String role);
}


