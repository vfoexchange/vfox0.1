package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.dao.ServicesDao;
import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.dao.UserRoleDao;
import com.vfoexchange.restServer.dto.ClientDetailsDTO;
import com.vfoexchange.restServer.dto.LinkedServicesDTO;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.dto.UserProfileDTO;
import com.vfoexchange.restServer.model.Services;
import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.model.UserRole;
import com.vfoexchange.restServer.service.UserService;
import com.vfoexchange.restServer.util.AppUtil;
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
    Method for advisor registration, encoding password before inserting in db and adding user
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
    Method for Client registration, encoding password before inserting in db and adding user
    */
    public void addClient(ClientDetailsDTO clientDetailsDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserRole userRole = userRoleDao.findByRole(clientDetailsDTO.getRole());
        User user = new User();
        user.setUsername(clientDetailsDTO.getUsername());
        user.setRoleId(userRole.getId());
        user.setPassword(passwordEncoder.encode(clientDetailsDTO.getPassword()));
        userDao.addClient(user);
        //Adding advisor=client relation
        userDao.addAdvisorClient(clientDetailsDTO.getUsername(), clientDetailsDTO.getAdvisorId());
    }

    /*
    Method for fetching user profile by username
     */
    public UserProfileDTO getUserProfile(String username) {
        User user = userDao.findByUsername(username);
        UserRole userRole = userRoleDao.findByRoleId(user.getRoleId());

        UserProfileDTO userProfile = new UserProfileDTO();
        userProfile.setUserId(user.getId());
        userProfile.setUsername(username);
        userProfile.setFirstLogin(user.isFirstLogin());
        userProfile.setRoleId(user.getRoleId());
        userProfile.setRole(userRole.getRole());
        return userProfile;
    }

    /*
    Method for fetching list of active advisor services for advisor or advisor's client
    */
    public List<Services> getAdvisorServices(String username) {
        User user = userDao.findByUsername(username);
        UserRole userRole = userRoleDao.findByRoleId(user.getRoleId());
        if (userRole.getRole().equalsIgnoreCase("advisor")) {
            List<Services> list = servicesDao.findActiveAdvisorServices(user.getId());
            return list;
        } else {
            User advisor = userDao.findAdvisorByClient(user.getId());
            List<Services> list = servicesDao.findActiveAdvisorServices(advisor.getId());
            return list;
        }
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

    /*
    Method for user verification by username
    */
    public void userVerification(String username) {
        userDao.userVerification(AppUtil.getDecodedString(username));
    }

    public boolean isValidUser(String username){
        boolean result = false;
        try {
            User user = userDao.findByUsername(username);
            if (user != null)
                result = true;
        }catch (Exception e){
            result = false;
        }
        return result;
    }
}
