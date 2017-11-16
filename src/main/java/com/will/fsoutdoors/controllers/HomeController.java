package com.will.fsoutdoors.controllers;


import com.will.fsoutdoors.models.User;
import com.will.fsoutdoors.repos.RoleRepository;
import com.will.fsoutdoors.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @RequestMapping("/")
    public String index(Model model,
                        Principal principal) {
        if (principal != null) {
            User me = userRepo.findByUsername(principal.getName());
            model.addAttribute("user", me);
            return "index";
        }
        return "index";

    }
}
