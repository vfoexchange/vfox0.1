package com.vfoexchange.restServer.model;

public class Captcha {

    private String statusCode;
    private String captchCode;
    private byte[] captcha;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCaptchCode() {
        return captchCode;
    }

    public void setCaptchCode(String captchCode) {
        this.captchCode = captchCode;
    }

    public byte[] getCaptcha() {
        return captcha;
    }

    public void setCaptcha(byte[] captcha) {
        this.captcha = captcha;
    }
}
