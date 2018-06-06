package com.vfoexchange.restServer.serviceImpl;

import com.vfoexchange.restServer.dao.ServicesDao;
import com.vfoexchange.restServer.dao.UserDao;
import com.vfoexchange.restServer.model.ServiceProviders;
import com.vfoexchange.restServer.model.User;
import com.vfoexchange.restServer.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ServicesDao servicesDao;

    @Autowired
    private UserDao userDao;

    /*
    Method to fetch serviceProviders using service name
     */
    public List<ServiceProviders> getServiceProviders(String serviceName) {
        List<ServiceProviders> list = servicesDao.findServiceProviders(serviceName);
        return list;
    }

    /*
    Method to update provider billing using provider name and user name
    */
    public void updateProviderBilling(String userName, String providerName) {
        User user = userDao.findByUsername(userName);
        ServiceProviders provider = servicesDao.findProviderByName(providerName);
        servicesDao.updateProviderBilling(user.getId(), provider.getServiceId(), provider.getId());
    }

}
