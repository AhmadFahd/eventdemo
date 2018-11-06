package com.ahmadfahd.Services;

import com.ahmadfahd.entity.UsersEntity;
import java.util.List;

public interface UserServices {

     List<UsersEntity> getAllUsers();
     List<UsersEntity> findAllPresent();
     UsersEntity findById(Long userid) ;
     void addUser(UsersEntity usersEntity, Long roleid);
     void updateUser(UsersEntity usersEntity,Long userid) ;
     void deleteById(Long userid);

    }