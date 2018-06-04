package com.vfoexchange.restServer.daoImpl;

import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.exceptions.UserNotFoundException;
import com.vfoexchange.restServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    Method to add new user(user can be advisor, client or admin)
     */
    @Transactional
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO User (UserName, Password, RoleId, PasswordSetDate, UserState, createdBy, CreatedAt, UpdatedBy, UpdatedAt) VALUES(?, ?, ?, now(), 'A', 1, now(), 1, now())",
                user.getUsername(), user.getPassword(), user.getRoleId());
    }

    /*
    Method used to fetch user from DB using username
     */
    @Override
    public User findByUsername(String username) {
        User user = (User) jdbcTemplate.queryForObject("SELECT * FROM User where UserName = ? ",
                new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
        if(user == null)
            throw new UserNotFoundException(username);
        return user;
    }
}

