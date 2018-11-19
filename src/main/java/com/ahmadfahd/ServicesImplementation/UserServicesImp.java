package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import com.ahmadfahd.security.Authority;
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
        List<UsersEntity> usersEntities = usersRepository.findByDeletedIsFalse();
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

    @Override
    public ResponseEntity addUser(UsersDTO usersDTO, Long roleid) {
        UsersEntity usersEntity ;
        usersEntity = modelMapper.map(usersDTO,UsersEntity.class);
//        usersEntity.setRoleid(rolesRepository.findById(roleid).get());
        return ResponseEntity.ok(usersRepository.save(usersEntity));
    }

    @Override
    public ResponseEntity updateUser(UsersDTO usersDTO, Long userid) {

            if (usersRepository.findById(userid).isPresent()) {

                UsersEntity usersEntity1 = usersRepository.findById(userid).get();
                UsersEntity usersEntity ;
                usersEntity = modelMapper.map(usersDTO, UsersEntity.class);
                usersEntity.setId(userid);
//                usersEntity.setRoleid(usersEntity1.getRoleid());
                return ResponseEntity.ok(usersRepository.save(usersEntity));
            }
            return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity deleteUser(Long userid) {
        if (usersRepository.findById(userid).isPresent()) {
            UsersEntity usersEntity = usersRepository.findById(userid).get();
            usersEntity.setDeleted(true);
            return ResponseEntity.ok(usersRepository.save(usersEntity));
        }
        return ResponseEntity.noContent().build();
    }
}