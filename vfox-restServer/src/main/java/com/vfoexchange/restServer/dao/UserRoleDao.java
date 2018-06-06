package com.vfoexchange.restServer.dao;

import com.vfoexchange.restServer.model.UserRole;

public interface UserRoleDao {

    public UserRole findByRole(String role);

    public UserRole findByRoleId(int roleId);

}
