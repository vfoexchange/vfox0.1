package com.vfoexchange.restServer.dao;

import com.vfoexchange.restServer.model.User;

public interface UserDao {

    public void add(User user);

    public User findByUsername(String username);

}
