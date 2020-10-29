package com.example.crud.service;

import com.example.crud.dao.RoleDao;
import com.example.crud.dao.UserDao;
import com.example.crud.model.Role;
import com.example.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {

        if(user.getRoles() == null) {
            Set<Role> role = new HashSet<>();
            role.add(roleDao.findById(1L));
            user.setRoles(role);
        }
        userDao.save(user);
    }

    @Override
    public User findById(Long id) {

        return userDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User get(Long id) {

        return userDao.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return userDao.allRoles();
    }
}
