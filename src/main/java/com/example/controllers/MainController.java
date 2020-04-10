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

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }
    @GetMapping("/main")
    public String mainPage(Model model,
                           @RequestParam(required = false, defaultValue = "") String filter){
        Iterable<Message> messages = messageRepository.findAll();
        if (filter!=null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        }else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter );
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


}
