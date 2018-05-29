package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.dao.UserRoleDao;
import com.vfoexchange.restServer.dto.UserDTO;
import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.model.UserRole;
import com.vfoexchange.restServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    /*
    Method for encoding password before inserting in db and adding user as per new registration
     */
    public void addUser(UserDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserRole userRole = new UserRole();
        userRole = userRoleDao.findByRole(userDTO.getRole());

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setRoleId(userRole.getId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDao.add(user);
    }


    public User find(int userId) {
        return userDao.find(userId);
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
