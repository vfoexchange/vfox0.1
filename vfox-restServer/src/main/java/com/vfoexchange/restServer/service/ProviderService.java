package com.vfoexchange.restServer.service;

import com.vfoexchange.restServer.model.ServiceProviders;
import java.util.List;

public interface ProviderService {

    public List<ServiceProviders> getServiceProviders(String serviceName);

}
