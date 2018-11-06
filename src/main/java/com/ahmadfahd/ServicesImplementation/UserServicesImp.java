package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<UsersEntity> findAllPresent() {
        return usersRepository.findByDeletedIsFalse();
    }

    @Override
    public UsersEntity findById(Long userid) {
//you can use optional and delete get();
    return usersRepository.findById(userid).get();
    }

    @Override
    public void addUser(UsersEntity usersEntity,Long roleid) {

        usersEntity.setRoleid(rolesRepository.findById(roleid).get());
        usersRepository.save(usersEntity);


    }

    @Override
    public void updateUser(UsersEntity usersEntity, Long userid) {
        UsersEntity usersEntity1= usersRepository.findById(userid).get();
//        usersEntity1.setRoleid(usersEntity1.getRoleid());

//        usersEntity1.setUsername(usersEntity.getUsername());
//        usersEntity1.setFirstname(usersEntity.getFirstname());
//        usersEntity1.setMidname(usersEntity.getMidname());
//        usersEntity1.setLastname(usersEntity.getLastname());
        usersEntity.setEmail(usersEntity.getEmail());
//        usersEntity1.setUserphone(usersEntity.getUserphone());
//        usersEntity1.setPassword(usersEntity.getPassword());
//        usersEntity1.setUsergender(usersEntity.getUsergender());
//        usersEntity1.setUserdob(usersEntity.getUserdob());
        usersRepository.save(usersEntity1);

    }

    @Override
    public void deleteById(Long userid) {
        UsersEntity usersEntity = usersRepository.findById(userid).get();
        usersEntity.setDeleted(true);
        usersRepository.save(usersEntity);
    }
}
