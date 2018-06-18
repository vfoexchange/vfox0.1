package com.vfoexchange.restServer.dto;

import java.util.HashMap;
import java.util.Map;

public class LinkedServicesDTO {

    private String username;
    private Map<String, Boolean> services = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Boolean> getServices() {
        return services;
    }

    public void setServices(Map<String, Boolean> services) {
        this.services = services;
    }
}

