package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.RoleServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.RolesDTO;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServicesImp implements RoleServices {

    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<RolesDTO> getAllRoles() {

        List<RolesEntity> rolesEntityList = rolesRepository.findAll();
        List<RolesDTO> rolesDTOList = ObjectMapperUtils.mapAll(rolesEntityList,RolesDTO.class);
        return rolesDTOList;
    }

    @Override
    public RolesDTO findById(Long roleid) {
            RolesEntity rolesEntity = rolesRepository.findById(roleid).get();
            RolesDTO rolesDTO = modelMapper.map(rolesEntity,RolesDTO.class);
            return rolesDTO;

    }

    @Override
    public List<RolesDTO> findByUser(UsersDTO usersDTO){
        List<RolesEntity> rolesEntity = rolesRepository.findByUserUsername(usersDTO.getUsername());
        List<RolesDTO> rolesDTO =ObjectMapperUtils.mapAll(rolesEntity,RolesDTO.class);

        return rolesDTO;
    }

    @Override
    public void giveRole(Long id, String role) {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRoleName(role);
        rolesEntity.setUser(usersRepository.findById(id).get());
        rolesRepository.save(rolesEntity);
    }
}