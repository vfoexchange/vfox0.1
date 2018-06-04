package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.dao.ServicesDao;
import com.vfoexchange.restServer.model.ServiceProviders;
import com.vfoexchange.restServer.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ServicesDao servicesDao;

    /*
    Method to fetch serviceProviders using service name
     */
    public List<ServiceProviders> getServiceProviders(String serviceName) {
        List<ServiceProviders> list = servicesDao.findServiceProviders(serviceName);
        return list;
    }

}
