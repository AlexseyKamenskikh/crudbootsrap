package com.example.crud.dao;

import com.example.crud.model.Role;

public interface RoleDao {
    Role findById(Long id);
}