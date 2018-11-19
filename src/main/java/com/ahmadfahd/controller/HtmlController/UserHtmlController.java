package com.ahmadfahd.controller.HtmlController;

import com.ahmadfahd.ServicesImplementation.UserServicesImp;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserHtmlController {


    @Autowired
    private UserServicesImp userServices;
    @Autowired
    private UsersRepository usersRepository;

    //-------------------HTML MODEL-------------
    @GetMapping
    public String findAllPresent(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String showAddUserPage() {
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(UsersEntity user) {
        usersRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return "redirect:/users";
    }


    @GetMapping("/edit/{id}")
    public String showEditUserPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", usersRepository.findById(id));
        return "edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@RequestBody UsersEntity upuser) {
        UsersEntity user = new UsersEntity();
                user.setId(upuser.getId());
                user.setUsername(upuser.getUsername());
                user.setPassword(upuser.getPassword());
        usersRepository.save(user);
        return "redirect:/users";
    }

}
