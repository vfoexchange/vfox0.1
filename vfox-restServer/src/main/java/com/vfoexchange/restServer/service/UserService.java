package com.vfoexchange.restServer.service;

import com.vfoexchange.restServer.dto.*;
import com.vfoexchange.restServer.model.Services;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    public void addUser(UserDTO userDTO);

    public void addClient(ClientDetailsDTO clientDetailsDTO);

    public UserProfileDTO getUserProfile(String username);

    public List<Services> getAdvisorServices(String username);

    public void updateAdvisorServices(LinkedServicesDTO linkedServicesDTO);

    public void userVerification(String username);

    public boolean isAleadyExist(String username);

    public void captureContactDetails(EnquiringUserDTO enquiringUserDTO);

}
