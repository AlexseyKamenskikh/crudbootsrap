package com.example.crud.dao;

import com.example.crud.model.Role;
import com.example.crud.model.User;
import java.util.List;

public interface UserDao {

    List<User> findAll();
    void save(User user);
    User findById(Long id);
    void deleteById(Long id);
    void update(User user);
    public User findByUsername(String username);
    User findByEmail(String username);
    List<Role> allRoles();
}