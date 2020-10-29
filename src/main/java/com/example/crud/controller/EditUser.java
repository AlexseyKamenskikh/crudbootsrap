package com.example.crud.controller;

import com.example.crud.dao.RoleDao;
import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller
public class EditUser {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleDao roleDao;


    @PostMapping("/delete")
    public String deleteUserForm(@ModelAttribute("authorizedUser") User user) {
        userServiceImpl.deleteById(user.getId());
        return "redirect:/userManager";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("newUser") User user, @RequestParam(value = "admin", required = false) String userSelectRoles) {
        Set<Role> roles = new HashSet<>();

        if( userSelectRoles!= null && userSelectRoles.contains("1")) {
            roles.add(roleDao.findById(2L));
        }
        if( userSelectRoles!= null && userSelectRoles.contains("0")) {
            roles.add(roleDao.findById(1L));
        }
        user.setRoles(roles);

        try {
            userServiceImpl.save(user);
        }catch (Exception e) {
            return "redirect:/userManager";
        }
        return "redirect:/userManager";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveEditUser(@ModelAttribute("user") User user , @RequestParam(value = "admin", required = false) String userSelectRoles) {

        user.setRoles(userServiceImpl.get(user.getId()).getRoles());

        if( userSelectRoles!= null && userSelectRoles.contains("1")) {
            user.addRole(roleDao.findById(2L));
        }
        else {
            user.deleteRole(roleDao.findById(2L).getName());
            user.addRole(roleDao.findById(1L));
        }
        user.setPassword(userServiceImpl.get(user.getId()).getPassword());

        userServiceImpl.update(user);
        return "redirect:/userManager";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        return "login";
    }

}