package com.vfoexchange.restServer.daoImpl;

import com.vfoexchange.restServer.dao.ServicesDao;
import com.vfoexchange.restServer.model.ServiceProviders;
import com.vfoexchange.restServer.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Qualifier("serviceDao")
public class ServicesDaoImpl implements ServicesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    Method used to fetch advisor services list from DB using advisor id
     */
    @Override
    public List<Services> findAdvisorServices(int advisorId) {
        List<Services> list = jdbcTemplate.query("SELECT * FROM Services where Id IN (Select ServiceId from AdvisorServices where AdvisorServicesState='A' and UserId = ?) ",
                new Object[]{advisorId}, new BeanPropertyRowMapper<>(Services.class));
        return list;
    }

    /*
    Method to fetch serviceProvider using service name
     */
    @Override
    public List<ServiceProviders> findServiceProviders(String serviceNmae) {
        List<ServiceProviders> list = jdbcTemplate.query("SELECT * FROM ServiceProvider where ServiceId IN (Select id from Services where Name= ?) ",
                new Object[]{serviceNmae}, new BeanPropertyRowMapper<>(ServiceProviders.class));
        return list;
    }

}

