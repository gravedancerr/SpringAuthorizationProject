package com.authorizationsystem.service.impl;

import com.authorizationsystem.dao.RoleDao;
import com.authorizationsystem.model.Role;
import com.authorizationsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    @Transactional
    public void create(Role role) {
        roleDao.create(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    @Transactional
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    @Transactional
    public Role findByName(String Name) {
        return roleDao.findByName(Name);
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public Role findById(Long id) {
        return roleDao.findById(id);
    }
}
