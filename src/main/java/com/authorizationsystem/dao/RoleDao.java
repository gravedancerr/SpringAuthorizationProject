package com.authorizationsystem.dao;

import com.authorizationsystem.model.Role;

public interface RoleDao extends Dao<Role> {
    /**
     * Inserts a new record
     * <p></p>
     * @param role instance of Role
     */
    @Override
    void create(Role role);

    /**
     * Updates a record
     * <p></p>
     * @param role instance of Role
     */
    @Override
    void update(Role role);

    /**
     * Deletes a record
     * <p></p>
     * @param role instance of Role
     */
    @Override
    void remove(Role role);

    /**
     * Searches for a record by name
     * <p></p>
     * @param name role name
     * @return role
     */
    Role findByName(String name);
}
