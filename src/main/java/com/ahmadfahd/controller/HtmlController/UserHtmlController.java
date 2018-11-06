package com.ahmadfahd.controller.HtmlController;

import com.ahmadfahd.ServicesImplementation.UserServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserHtmlController {
    private UserServicesImp userServices;

    @Autowired
    public UserHtmlController(UserServicesImp userServices) {
        super();
        this.userServices = userServices;
    }

    //-------------------HTML MODEL-------------
    @GetMapping
    public String findAllPresent(Model model) {
        model.addAttribute("users", this.userServices.findAllPresent());
        return "users";
    }
}
