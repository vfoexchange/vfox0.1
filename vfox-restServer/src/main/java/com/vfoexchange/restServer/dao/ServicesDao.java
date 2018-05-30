package com.vfoexchange.restServer.dao;

import com.vfoexchange.restServer.model.Services;

import java.util.List;

public interface ServicesDao {

    public List<Services> findAdvisorServices(int advisorId);

}
