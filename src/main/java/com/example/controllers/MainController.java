package com.example.controllers;

import com.example.dao.models.Message;
import com.example.dao.models.User;
import com.example.dao.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("username", user.getUsername());
        return "greeting";
    }
    @GetMapping("/main")
    public String mainPage(Model model){
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }
    @PostMapping("main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             @RequestParam String tag,
                             Model model){

        Message message = new Message(text, tag, user);
        messageRepository.save(message);

        return "redirect:/main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Model model){
        Iterable<Message> messages;
        if (filter!=null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        }else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);

        return "main";
    }

}
