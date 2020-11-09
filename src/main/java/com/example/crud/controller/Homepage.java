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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class Homepage {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping(value = {"/userManager", "/admin"})
    public String home(ModelMap model) {
        List<User> listUsers = userServiceImpl.findAll();
        model.addAttribute("users", listUsers);

        List<Role> allRoles = userServiceImpl.getAllRoles();
        model.addAttribute("roles", allRoles);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("authorizedUser", userServiceImpl.findByUsername(userDetails.getUsername()));
        return "index";
    }

    @GetMapping(value = {"/", "/hello"})
    public String hello(ModelMap modelMap) {
//    public String hello(ModelMap modelMap, @RequestParam long id) {

        modelMap.addAttribute("message", "Hello war");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        modelMap.addAttribute("authorizedUser", userServiceImpl.findByUsername(userDetails.getUsername()));
        return "hello";
    }

}