package com.authorizationsystem.service;

import com.authorizationsystem.model.Role;

import java.util.List;

public interface RoleService {
    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String Name);

    List<Role> findAll();

    Role findById(Long id);
}
