package com.example.crud.service;


import com.example.crud.model.Role;
import com.example.crud.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();
    void save(User user);
    User findById(Long id);
    void deleteById(Long id);
    void update(User user);
    User findByUsername (String username);
    User get(Long id);
    List<Role> getAllRoles();

}