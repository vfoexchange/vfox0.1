package com.vfoexchange.restServer.dao;

import com.vfoexchange.restServer.model.ServiceProviders;
import com.vfoexchange.restServer.model.Services;
import java.util.List;

public interface ServicesDao {

    public List<Services> findAdvisorServices(int advisorId);

    public List<ServiceProviders> findServiceProviders(String serviceName);

}
