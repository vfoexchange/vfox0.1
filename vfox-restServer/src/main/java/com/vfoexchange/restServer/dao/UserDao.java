package com.vfoexchange.restServer.dao;

import com.vfoexchange.restServer.model.User;

public interface UserDao {

    public void add(User user);

    public void addClient(User user);

    public void addAdvisorClient(String clientUserName, int advisorId);

    public User findByUsername(String username);

    public User findAdvisorByClient(int clientId);

    public void userVerification(String username);

}
