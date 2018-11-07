package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.RoleServices;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServicesImp implements RoleServices {


    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<RolesEntity> getAllRoles() { return rolesRepository.findAll(); }

    @Override
    public Optional<RolesEntity> findById(Long roleid) { return rolesRepository.findById(roleid); }

}