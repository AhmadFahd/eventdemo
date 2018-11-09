package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.RoleServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.RolesDTO;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.repository.RolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServicesImp implements RoleServices {

    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity getAllRoles() {

        List<RolesEntity> rolesEntityList = rolesRepository.findAll();
        List<RolesDTO> rolesDTOS = ObjectMapperUtils.mapAll(rolesEntityList, RolesDTO.class);
        return ResponseEntity.ok(rolesDTOS);
    }

    @Override
    public ResponseEntity findById(Long roleid) {
        if (rolesRepository.findById(roleid).isPresent()) {
            RolesEntity rolesEntity = rolesRepository.findById(roleid).get();
            RolesDTO rolesDTO = modelMapper.map(rolesEntity,RolesDTO.class);
            return ResponseEntity.ok(rolesDTO);
        }
        return ResponseEntity.noContent().build();
    }

}