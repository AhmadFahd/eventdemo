package com.ahmadfahd.Services;

import com.ahmadfahd.dto.UsersDTO;
import org.springframework.http.ResponseEntity;

public interface UserServices {

     ResponseEntity getAllUsers();
     ResponseEntity findAllPresent();
     ResponseEntity findById(Long userid) ;
     ResponseEntity addUser(UsersDTO usersDTO, Long roleid);
     ResponseEntity updateUser(UsersDTO usersDTO, Long userid);
     ResponseEntity deleteUser(Long userid);

    }