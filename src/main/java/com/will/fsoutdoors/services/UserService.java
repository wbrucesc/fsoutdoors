package com.will.fsoutdoors.services;

import com.will.fsoutdoors.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
