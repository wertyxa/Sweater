package com.example.controllers;

import com.example.dao.models.User;
import com.example.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;


    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }
    @PostMapping("/register")
    public String register(
                User user
                ,Model model){
        if (!userService.addUser(user)){
            model.addAttribute("message", "User Exist!");
            return "register";
        }

        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code,
                           Model model){
        boolean isActivated = userService.activateUser(code);

        if (isActivated){
            model.addAttribute("message", "User successful activated");
        }else {
            model.addAttribute("message", "Activation code is no found!");
        }

        return "login";
    }
}
