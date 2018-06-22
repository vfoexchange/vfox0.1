package com.vfoexchange.restServer.dao;

import com.vfoexchange.restServer.dto.AdvisorWebsiteDTO;
import com.vfoexchange.restServer.model.AdvisorWebsite;
import com.vfoexchange.restServer.model.User;

public interface UserDao {

    public void add(User user);

    public void addClient(User user);

    public void addAdvisorClient(String clientUserName, int advisorId);

    public User findByUsernameWithState(String username);

    public User findByUsername(String username);

    public User findAdvisorByClient(int clientId);

    public void userVerification(String username);

    public void saveAdvisorWebsite(AdvisorWebsite advisorWebsite);

    public AdvisorWebsiteDTO fetchAdvisorWebsite(String advisorUsername);

}
