package com.vfoexchange.restServer.daoImpl;

import com.vfoexchange.restServer.dao.UserRoleDao;
import com.vfoexchange.restServer.exceptions.UserRoleNotFoundException;
import com.vfoexchange.restServer.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("userDao")
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    Method used to fetch userrole object from DB using role name
     */
    @Override
    public UserRole findByRole(String role) {
        UserRole userRole = (UserRole) jdbcTemplate.queryForObject("SELECT * FROM UserRole where Role = ? ",
                new Object[]{role}, new BeanPropertyRowMapper<>(UserRole.class));

        if (userRole == null)
            throw new UserRoleNotFoundException(role);
        return userRole;
    }

    /*
    Method used to fetch userrole object from DB using role id
    */
    @Override
    public UserRole findByRoleId(int roleId) {
        UserRole userRole = (UserRole) jdbcTemplate.queryForObject("SELECT * FROM UserRole where id = ? ",
                new Object[]{roleId}, new BeanPropertyRowMapper<>(UserRole.class));

        if (userRole == null)
            throw new UserRoleNotFoundException(String.valueOf(roleId));
        return userRole;
    }
}

