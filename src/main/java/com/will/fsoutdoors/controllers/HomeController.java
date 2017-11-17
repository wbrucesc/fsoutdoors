package com.will.fsoutdoors.controllers;


import com.will.fsoutdoors.models.Event;
import com.will.fsoutdoors.models.User;
import com.will.fsoutdoors.repos.EventRepository;
import com.will.fsoutdoors.repos.RoleRepository;
import com.will.fsoutdoors.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private EventRepository eventRepo;
    // home page
    @RequestMapping("/")
    public String index(Model model,
                        Principal principal) {
        if (principal != null) {
            User me = userRepo.findByUsername(principal.getName());
            model.addAttribute("user", me);
            model.addAttribute("events", eventRepo.findAll());
            return "index";
        }
        return "index";
    }

    // new event form
    @RequestMapping("/newEvent")
    public String eventForm(Model model,
                            Principal principal) {
        if (principal != null) {
            User me = userRepo.findByUsername(principal.getName());
            model.addAttribute("user", me);
            model.addAttribute("event", new Event());
            return "newEvent";
        }
        model.addAttribute("event", new Event());
        return "newEvent";
    }

    // creates new event
    @RequestMapping(value = "/newEvent", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute Event event,
                              Principal principal) {
        User me = userRepo.findByUsername(principal.getName());
        event.setHost(me);
        event.setCreated(new Date());
        event.setActive(true);
        eventRepo.save(event);
        return "redirect:/";
    }
}
