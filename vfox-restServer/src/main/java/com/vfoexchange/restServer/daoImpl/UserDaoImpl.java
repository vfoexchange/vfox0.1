package com.vfoexchange.restServer.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.model.User;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    Method to add new user(as of now only for adviser registration, later should be moved to generic one)
     */
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO User (UserName, Password, RoleId, PasswordSetDate, UserState, createdBy, CreatedAt, UpdatedBy, UpdatedAt) VALUES(?, ?, 2, now(), 'A', 1, now(), 1, now())",
                user.getUsername(), user.getPassword());
    }

    public User find(int userId) {
        User user = (User) jdbcTemplate.queryForObject("SELECT * FROM users where id = ? ",
                new Object[]{userId}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    /*
    Method used to fetch user from DB while login authentication
     */
    @Override
    public User findByUsername(String username) {
        User user = (User) jdbcTemplate.queryForObject("SELECT * FROM User where UserName = ? ",
                new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }
}

