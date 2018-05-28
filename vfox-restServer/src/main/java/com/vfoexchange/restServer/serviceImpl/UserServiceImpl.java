package com.vfoexchange.restServer.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /*
    Method for encoding password before inserting in db and adding user as per new registration
     */
    public void addUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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
