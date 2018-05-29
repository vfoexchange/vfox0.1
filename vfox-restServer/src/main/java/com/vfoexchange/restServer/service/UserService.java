package com.vfoexchange.restServer.service;

import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public void addUser(UserDTO userDTO);

    public User find(int userId);
}
