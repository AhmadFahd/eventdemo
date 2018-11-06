package com.ahmadfahd.controller.ApiController;

import com.ahmadfahd.NotificationService;
import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/UsersAccess/view")
    public List<UsersEntity> findAllPresent() {
        return userServices.findAllPresent();
    }

    @GetMapping("/AdminAccess/view")
    public List<UsersEntity> getAllUser() {
        return userServices.getAllUsers();
    }

    @GetMapping("/view/{userid}")
    public UsersEntity findById(@PathVariable Long userid) {
        UsersEntity usersEntity;
        return userServices.findById(userid);
    }

    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody UsersEntity usersEntity) {
        userServices.addUser(usersEntity, Long.valueOf(1));
    }
    @PostMapping("/addOrganizer")
    public void addOrganizer(@RequestBody UsersEntity usersEntity) {
        userServices.addUser(usersEntity, Long.valueOf(2));
    }
    @PostMapping("/addUser")
    public void addUser(@RequestBody UsersEntity usersEntity) {


        userServices.addUser(usersEntity, Long.valueOf(3));
        notificationService.addUserNotification(usersEntity);
    }


    @PutMapping("/edit/{userid}")
    public void updateUser(@RequestBody UsersEntity usersEntity, @PathVariable Long userid) {
        usersEntity.setRoleid(usersEntity.getRoleid());

        userServices.updateUser(usersEntity, userid);

    }

    @PutMapping("/delete/{userid}")
    public void deleteById(@PathVariable Long userid) {
        userServices.deleteById(userid);
    }


}