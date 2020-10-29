package com.example.crud.controller;

import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class NewUser {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "/create")
    public String createNewUser(ModelMap model) {
        User newUser = new User();
        model.addAttribute("createNewUser", newUser);

        List<Role> allRoles = userServiceImpl.getAllRoles();
        model.addAttribute("roles", allRoles);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("authorizedUser", userServiceImpl.findByUsername(userDetails.getUsername()));

        return "createUser";
    }


}