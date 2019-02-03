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
     UsersDTO findByEmail(String email);
     boolean isUser(String username);
     boolean isUserByEmail(String email);
     boolean isActiveUserByEmail(String email);
     List<UserGetDto> findAllOrganizers();


     }