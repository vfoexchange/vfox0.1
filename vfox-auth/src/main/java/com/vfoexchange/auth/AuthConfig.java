package com.vfoexchange.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
Configurable properties related to auth security
 */
@Component
@ConfigurationProperties(prefix = "security.oauth2")
public class AuthConfig {
    private String authPath;
    private String clientId;
    private String clientSecret;
    private int authTokenValidity;
    private String keyStorePassword;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAuthPath() {
        return authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }

    public int getAuthTokenValidity() {
        return authTokenValidity;
    }

    public void setAuthTokenValidity(int authTokenValidity) {
        this.authTokenValidity = authTokenValidity;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }
}

