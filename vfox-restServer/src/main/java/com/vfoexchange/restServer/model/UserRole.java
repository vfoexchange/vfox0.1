package com.vfoexchange.restServer.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class UserRole {

    private int id;
    private String role;
    private String roleState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        role = role;
    }

    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        roleState = roleState;
    }
}
