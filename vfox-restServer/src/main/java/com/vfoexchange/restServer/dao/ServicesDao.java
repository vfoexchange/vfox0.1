package com.vfoexchange.restServer.dao;

import com.vfoexchange.restServer.model.ServiceProviders;
import com.vfoexchange.restServer.model.Services;
import java.util.List;

public interface ServicesDao {

    public List<Services> findActiveAdvisorServices(int advisorId);

    public List<ServiceProviders> findServiceProviders(String serviceName);

    public List<Services> findAdvisorServices(int advisorId);

    public void updateAdvisorServices(String status, int advisorId, int serviceId);

    public void addAdvisorServices(int advisorId, String serviceName);

    public ServiceProviders findProviderByName(String providerName);

    public void updateProviderBilling(int userId, int serviceId, int serviseProviderId);

}
