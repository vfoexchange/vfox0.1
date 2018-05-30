package com.vfoexchange.restServer.model;


public class Services {

    private int id;
    private String name;
    private boolean serviceState;
    private String shortDesc;
    private String longDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isServiceState() {
        return serviceState;
    }

    public void setServiceState(boolean serviceState) {
        this.serviceState = serviceState;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
}
