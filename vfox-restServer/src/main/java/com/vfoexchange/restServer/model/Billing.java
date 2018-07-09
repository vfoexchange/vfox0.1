package com.vfoexchange.restServer.model;

public class Billing {

    private int id;
    private int ServiceId;
    private int ServiceProviderId;
    private int UserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return ServiceId;
    }

    public void setServiceId(int serviceId) {
        ServiceId = serviceId;
    }

    public int getServiceProviderId() {
        return ServiceProviderId;
    }

    public void setServiceProviderId(int serviceProviderId) {
        ServiceProviderId = serviceProviderId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
