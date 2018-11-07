package com.ahmadfahd.Services;

import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserServices {

     List<UsersEntity> getAllUsers();
     List<UsersEntity> findAllPresent();
     Optional<UsersEntity> findById(Long userid) ;
     UsersEntity addUser(UsersDTO usersDTO, Long roleid);
     UsersEntity updateUser(UsersDTO usersDTO, Long userid);
     UsersEntity deleteById(Long userid);

    }