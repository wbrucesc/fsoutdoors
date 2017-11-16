package com.will.fsoutdoors.controllers;

import com.will.fsoutdoors.models.Role;
import com.will.fsoutdoors.models.User;
import com.will.fsoutdoors.repos.RoleRepository;
import com.will.fsoutdoors.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // user signup page
    @RequestMapping(value ="/signup", method = RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // creates new user and redirects to login if successful
    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    public String signupForm(@ModelAttribute User user) {

        Role userRole = roleRepo.findByName("ROLE_USER");
        user.setRole(userRole);
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setActive(true);
        userRepo.save(user);
        return "redirect:/login";
    }

    // login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model,
                            HttpServletRequest request) {
        model.addAttribute("user", new User());
        Object message = request.getSession().getAttribute("error");
        model.addAttribute("errors", message);
        request.getSession().removeAttribute("error");
        return "login";
    }

}
