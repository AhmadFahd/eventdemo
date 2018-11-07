package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImp implements UserServices {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<UsersEntity> findAllPresent() {
        return usersRepository.findByDeletedIsFalse();
    }

    @Override
    public Optional<UsersEntity> findById(Long userid) {

    return usersRepository.findById(userid);
    }

    @Override
    public UsersEntity addUser(UsersDTO usersDTO, Long roleid) {
        UsersEntity usersEntity ;
        usersEntity = modelMapper.map(usersDTO,UsersEntity.class);
        usersEntity.setRoleid(rolesRepository.findById(roleid).get());
        return usersRepository.save(usersEntity);
    }

    public UsersEntity updateUser(UsersDTO usersDTO, Long userid) {


        UsersEntity usersEntity1 = usersRepository.findById(userid).get();
        UsersEntity usersEntity = new UsersEntity();
        usersEntity = modelMapper.map(usersDTO, UsersEntity.class);
        usersEntity.setUserid(userid);
        usersEntity.setRoleid(usersEntity1.getRoleid());
        return usersRepository.save(usersEntity);

    }

    @Override
    public UsersEntity deleteById(Long userid) {
        UsersEntity usersEntity = usersRepository.findById(userid).get();
        usersEntity.setDeleted(true);
        return usersRepository.save(usersEntity);
    }
}
