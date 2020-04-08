package com.example.controllers;

import com.example.dao.models.Role;
import com.example.dao.models.User;
import com.example.dao.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }
    @PostMapping("/register")
    public String register(
                User user
                ,Model model){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB!=null){
            model.addAttribute("message", "User Exist!");
            return "register";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
