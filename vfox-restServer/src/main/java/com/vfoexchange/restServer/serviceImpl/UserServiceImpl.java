package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.dao.ServicesDao;
import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.dao.UserRoleDao;
import com.vfoexchange.restServer.dto.LinkedServicesDTO;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.model.Services;
import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.model.UserRole;
import com.vfoexchange.restServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    ServicesDao servicesDao;

    /*
    Method for encoding password before inserting in db and adding user as per new registration
     */
    public void addUser(UserDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserRole userRole = userRoleDao.findByRole(userDTO.getRole());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setRoleId(userRole.getId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDao.add(user);
    }

    /*
    Method for fetching user by username
     */
    public User getUser(String username) {

        return userDao.findByUsername(username);
    }

    /*
    Method for fetching list of active advisor services
     */
    public List<Services> getAdvisorServices(String username) {
        User user = userDao.findByUsername(username);
        List<Services> list = servicesDao.findAdvisorServices(user.getId());
        return list;

    }

    /*
    Method for updating list of active advisor services
     */

    public void updateAdvisorServices(LinkedServicesDTO linkedServicesDTO) {
        User user = userDao.findByUsername(linkedServicesDTO.getUsername());
        List<Services> list = servicesDao.findAdvisorServices(user.getId());

    }

    /*
    Method which overrides from org.springframework.security.core.userdetails.UserDetails,
    responsible to search user from DB with supplied credentials
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

}
