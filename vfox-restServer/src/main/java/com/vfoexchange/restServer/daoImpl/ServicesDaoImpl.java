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
    Method used to fetch active advisor services list from DB using advisor id
     */
    @Override
    public List<Services> findActiveAdvisorServices(int advisorId) {
        List<Services> list = jdbcTemplate.query("SELECT * FROM Services where Id IN (Select ServiceId from AdvisorServices where AdvisorServicesState='A' and UserId = ?) ",
                new Object[]{advisorId}, new BeanPropertyRowMapper<>(Services.class));
        return list;
    }

    /*
    Method used to fetch advisor services list from DB using advisor id
    */
    @Override
    public List<Services> findAdvisorServices(int advisorId) {
        List<Services> list = jdbcTemplate.query("SELECT * FROM Services where Id IN (Select ServiceId from AdvisorServices where UserId = ?) ",
                new Object[]{advisorId}, new BeanPropertyRowMapper<>(Services.class));
        return list;
    }

    /*
    Method to fetch serviceProvider using service name
     */
    @Override
    public List<ServiceProviders> findServiceProviders(String serviceName) {
        List<ServiceProviders> list = jdbcTemplate.query("SELECT * FROM ServiceProvider where ServiceId IN (Select id from Services where Name= ?) ",
                new Object[]{serviceName}, new BeanPropertyRowMapper<>(ServiceProviders.class));
        return list;
    }

    /*
    Method to fetch serviceProvider using service provider name
    */
    @Override
    public ServiceProviders findProviderByName(String providerName) {
        ServiceProviders provider = jdbcTemplate.queryForObject("SELECT * FROM ServiceProvider where  Name= ? ",
                new Object[]{providerName}, new BeanPropertyRowMapper<>(ServiceProviders.class));
        return provider;
    }

    /*
    Method used to upadte advisor services list using advisor id and service id
    */
    @Override
    public void updateAdvisorServices(String status, int advisorId, int serviceId) {
        jdbcTemplate.update("UPDATE AdvisorServices set AdvisorServicesState = ? where UserId = ? and ServiceId = ? ",
                new Object[]{status, advisorId, serviceId});
    }

    /*
    Method used to insert advisor services using advisor id and service name
    */
    @Override
    public void addAdvisorServices(int advisorId, String serviceName) {
        jdbcTemplate.update("INSERT INTO vfox.AdvisorServices (UserId, ServiceId, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt, AdvisorServicesState) VALUES(?, (Select id from Services where Name= ?), 1, now(), 1, now(), 'A') ",
                new Object[]{advisorId, serviceName});
    }

    /*
    Method to update billing for a user with provider
     */
    @Override
    public void updateProviderBilling(int userId, int serviceId, int serviceProviderId) {
        jdbcTemplate.update("INSERT INTO vfox.Billing (ServiceId, ServiceProviderId, UserId, CreatedBy, CreatedAt, UpdatedBy, UpdatedAt) VALUES(?, ?, ?, 1, now(), 1, now()) ",
                new Object[]{serviceId, serviceProviderId, userId});
    }
}

