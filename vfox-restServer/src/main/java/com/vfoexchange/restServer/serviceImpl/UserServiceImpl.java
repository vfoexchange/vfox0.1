package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.dao.ServicesDao;
import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.dao.UserRoleDao;
import com.vfoexchange.restServer.dto.LinkedServicesDTO;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.dto.UserProfileDTO;
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
import java.util.Map;

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
    Method for fetching user profile by username
     */
    public UserProfileDTO getUserProfile(String username) {
        User user = userDao.findByUsername(username);
        UserRole userRole = userRoleDao.findByRoleId(user.getRoleId());

        UserProfileDTO userProfile = new UserProfileDTO();
        userProfile.setUsername(username);
        userProfile.setFirstLogin(user.isFirstLogin());
        userProfile.setRoleId(user.getRoleId());
        userProfile.setRole(userRole.getRole());
        return userProfile;
    }

    /*
    Method for fetching list of active advisor services
     */
    public List<Services> getAdvisorServices(String username) {
        User user = userDao.findByUsername(username);
        List<Services> list = servicesDao.findActiveAdvisorServices(user.getId());
        return list;

    }

    /*
    Method for adding/updating list of advisor services
     */
    public void updateAdvisorServices(LinkedServicesDTO linkedServicesDTO) {
        User user = userDao.findByUsername(linkedServicesDTO.getUsername());
        List<Services> list = servicesDao.findActiveAdvisorServices(user.getId());
        Map<String, Boolean> serviceMap = linkedServicesDTO.getServices();
        for (Map.Entry<String, Boolean> entry : serviceMap.entrySet()) {
            boolean isExisting = false;
            for (Services each : list) {
                if (entry.getKey().equalsIgnoreCase(each.getName())) {
                    isExisting = true;
                    //update db
                    if (entry.getValue().booleanValue()) {
                        servicesDao.updateAdvisorServices("A", user.getId(), each.getId());
                    } else {
                        servicesDao.updateAdvisorServices("I", user.getId(), each.getId());
                    }
                }
            }
            if (!isExisting && entry.getValue().booleanValue()) {
                //  insert in db
                servicesDao.addAdvisorServices(user.getId(), entry.getKey());
            }
        }
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
