package com.vfoexchange.restServer.daoImpl;

import com.vfoexchange.restServer.Constants.AppConstants;
import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.exceptions.UserAlreadyActiveException;
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
    Method to add new user(user can be advisor or admin)
     */
    @Transactional
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO User (UserName, Password, RoleId, PasswordSetDate, UserState, createdBy, CreatedAt, UpdatedBy, UpdatedAt) VALUES(?, ?, ?, now(), 'I', 1, now(), 1, now())",
                user.getUsername(), user.getPassword(), user.getRoleId());
    }

    /*
    Method to add new client user
    */
    @Transactional
    public void addClient(User user) {
        jdbcTemplate.update("INSERT INTO User (UserName, Password, RoleId, PasswordSetDate, UserState, createdBy, CreatedAt, UpdatedBy, UpdatedAt) VALUES(?, ?, ?, now(), 'A', 1, now(), 1, now())",
                user.getUsername(), user.getPassword(), user.getRoleId());
    }

    /*
    Method to add advisor-client relation
    */
    @Transactional
    public void addAdvisorClient(String clientUserName, int advisorId) {
        jdbcTemplate.update("INSERT INTO AdvisorClient (AdvisorId, ClientId, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt) VALUES(?, (Select id from User where UserName= ?), 1, now(), 1, now())",
                advisorId, clientUserName);
    }

    /*
    Method used to fetch advisor liked with client using clientID
    */
    @Override
    public User findAdvisorByClient(int clientId) {
        User user = (User) jdbcTemplate.queryForObject("SELECT * FROM User where id IN (select AdvisorId from AdvisorClient where ClientId = ?) ",
                new Object[]{clientId}, new BeanPropertyRowMapper<>(User.class));

        return user;
    }

    /*
    Method used to fetch user from DB using username and active state
     */
    @Override
    public User findByUsernameWithState(String username) {
        User user = (User) jdbcTemplate.queryForObject("SELECT * FROM User where UserState = 'A' and UserName = ? ",
                new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
        if (user == null)
            throw new UserNotFoundException(username);
        return user;
    }

    /*
    Method for marking user as verified using username
     */
    public void userVerification(String username) {
        int status = jdbcTemplate.update("Update User set UserState = 'A' where and UserState = ? and  UserName = ?",
                AppConstants.USER_INACTIVE_STATE, username);
        if (status == 0)
            throw new UserAlreadyActiveException(username);
    }

    /*
    Method used to fetch user from DB using username
     */
    @Override
    public User findByUsername(String username) {
        User user = (User) jdbcTemplate.queryForObject("SELECT * FROM User where  UserName = ? ",
                new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
        if (user == null)
            throw new UserNotFoundException(username);
        return user;
    }
}

