package com.ahmadfahd.Services;

import com.ahmadfahd.dto.UserGetDto;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServices {

     List<UserGetDto> getAllUsers();
     List<UserGetDto> findAllPresent();
     List<UserGetDto> findAllDisable();
     UserGetDto findById(Long userid) ;
     void addUser(UsersDTO usersDTO,String type);
     void updateUser(UsersDTO usersDTO, Long userid);
     void enableUser(Long userid,boolean b);
     UsersDTO findByUsername(String username);
     UsersDTO findByUsernameAndEnabledTrue(String username);
     boolean isActiveUser(String username);
     boolean isUser(String username);
     List<UserGetDto> findAllOrganizers();


     }