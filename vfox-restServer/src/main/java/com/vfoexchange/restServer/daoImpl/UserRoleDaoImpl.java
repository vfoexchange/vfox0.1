package com.vfoexchange.restServer.daoImpl;

import com.vfoexchange.restServer.dao.UserRoleDao;
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
    JdbcTemplate jdbcTemplate;

    /*
    Method used to fetch userrole object from DB using role name
     */
    @Override
    public UserRole findByRole(String role) {
        UserRole userRole = (UserRole) jdbcTemplate.queryForObject("SELECT * FROM UserRole where Role = ? ",
                new Object[]{role}, new BeanPropertyRowMapper<>(UserRole.class));
        return userRole;
    }
}

