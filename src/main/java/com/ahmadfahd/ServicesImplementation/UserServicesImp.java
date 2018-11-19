package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImp implements UserServices {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity getAllUsers() {
        List<UsersEntity> usersEntities = usersRepository.findAll();
        List<UsersDTO> usersDTOS = ObjectMapperUtils.mapAll(usersEntities,UsersDTO.class);
        return ResponseEntity.ok(usersDTOS);

    }

    @Override
    public ResponseEntity findAllPresent() {
        List<UsersEntity> usersEntities = usersRepository.findByEnabledIsFalse();
        List<UsersDTO> usersDTOS = ObjectMapperUtils.mapAll(usersEntities,UsersDTO.class);
        return ResponseEntity.ok(usersDTOS);
    }

    @Override
    public ResponseEntity findById(Long userid) {

        if(usersRepository.findById(userid).isPresent()){
        UsersEntity usersEntity = usersRepository.findById(userid).get();
        UsersDTO usersDTO = modelMapper.map(usersEntity,UsersDTO.class);
        return ResponseEntity.ok(usersDTO);
        }
        return ResponseEntity.noContent().build();
    }

    // FIXME: 11/19/2018
    @Override
    public ResponseEntity addUser(UsersDTO usersDTO, String role) {
        UsersEntity usersEntity ;
        RolesEntity rolesEntity = new RolesEntity();
        usersEntity = modelMapper.map(usersDTO,UsersEntity.class);
        rolesEntity.setRolename("ROLE_"+role);
        rolesEntity.setUser(usersEntity);
        rolesRepository.save(rolesEntity);
        return ResponseEntity.ok(usersRepository.save(usersEntity));
    }

    @Override
    public ResponseEntity updateUser(UsersDTO usersDTO, Long userid) {

            if (usersRepository.findById(userid).isPresent()) {
                UsersEntity usersEntity ;
                usersEntity = modelMapper.map(usersDTO, UsersEntity.class);
                usersEntity.setId(userid);
                return ResponseEntity.ok(usersRepository.save(usersEntity));
            }
            return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity deleteUser(Long userid) {
        if (usersRepository.findById(userid).isPresent()) {
            UsersEntity usersEntity = usersRepository.findById(userid).get();
            usersEntity.setEnabled(false);
            return ResponseEntity.ok(usersRepository.save(usersEntity));
        }
        return ResponseEntity.noContent().build();
    }
}