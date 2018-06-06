package com.vfoexchange.restServer.model;

public class UserRole {

    private int id;
    private String Role;
    private String RoleState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getRoleState() {
        return RoleState;
    }

    public void setRoleState(String roleState) {
        RoleState = roleState;
    }
}
