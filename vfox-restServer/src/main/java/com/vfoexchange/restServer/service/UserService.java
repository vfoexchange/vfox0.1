package com.vfoexchange.restServer.service;

import com.vfoexchange.restServer.dto.LinkedServicesDTO;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.model.Services;
import com.vfoexchange.restServer.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    public void addUser(UserDTO userDTO);

    public User getUser(String username);

    public List<Services> getAdvisorServices(String username);

    public void updateAdvisorServices(LinkedServicesDTO linkedServicesDTO);

}
