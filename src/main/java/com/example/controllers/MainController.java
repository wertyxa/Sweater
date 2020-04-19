package com.example.controllers;

import com.example.dao.models.Message;
import com.example.dao.models.User;
import com.example.dao.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String path;

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
                             @RequestParam ("file") MultipartFile file,
                             Model model) throws IOException {
        if(!text.isEmpty()&& text!=null) {
            Message message = new Message(text, tag, user);

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(path);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                message.setFilename(resultFilename);
                file.transferTo(new File(path + "/" + resultFilename));
            }

            messageRepository.save(message);
        }
        return "redirect:/main";
    }


}
