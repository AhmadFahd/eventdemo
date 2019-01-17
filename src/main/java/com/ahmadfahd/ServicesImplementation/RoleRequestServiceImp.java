package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.RoleRequestService;
import com.ahmadfahd.Services.RoleServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.RoleRequestDTO;
import com.ahmadfahd.entity.RoleRequestEntity;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.repository.RoleRequestRepository;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleRequestServiceImp implements RoleRequestService {

    @Autowired
    private RoleRequestRepository roleRequestRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<RoleRequestDTO> getAllRequests() {
        List<RoleRequestEntity> roleRequestEntities = roleRequestRepository.findAllByAcceptedFalse();
        List<RoleRequestDTO> roleRequestDTOS = ObjectMapperUtils.mapAll(roleRequestEntities,RoleRequestDTO.class);
        return roleRequestDTOS;
    }

    @Override
    public List<RoleRequestDTO> getRoleRequests(String role){
        List<RoleRequestEntity> roleRequestEntities = roleRequestRepository.findAllByAcceptedFalseAndRole(role);
        List<RoleRequestDTO> roleRequestDTOS = ObjectMapperUtils.mapAll(roleRequestEntities,RoleRequestDTO.class);
        return roleRequestDTOS;
    }

    @Override
    public void addRequest(Long uid, String role) {
        RoleRequestEntity roleRequestEntity = new RoleRequestEntity();
        roleRequestEntity.setRole(role);
        roleRequestEntity.setUser(usersRepository.findById(uid).get());
        roleRequestRepository.save(roleRequestEntity);
    }

    @Override
    public void acceptRoleRequest(Long rid) {
        RoleRequestEntity roleRequestEntity = roleRequestRepository.findById(rid).get();
        if (!roleRequestEntity.isAccepted()) {
            RolesEntity rolesEntity = new RolesEntity();
            rolesEntity.setUser(roleRequestEntity.getUser());
            rolesEntity.setRoleName(roleRequestEntity.getRole());
            rolesRepository.save(rolesEntity);
            roleRequestEntity.setAccepted(true);
            roleRequestRepository.save(roleRequestEntity);
        }
    }
}
