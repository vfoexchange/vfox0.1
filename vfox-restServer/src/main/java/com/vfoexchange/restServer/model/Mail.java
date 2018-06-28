package com.vfoexchange.restServer.model;

public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    private String bCC;

    public Mail() {
    }

    public Mail(String from, String to, String subject, String content, String bCC) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.bCC = bCC;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getbCC() {
        return bCC;
    }

    public void setbCC(String bCC) {
        this.bCC = bCC;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", bCC='" + bCC + '\'' +
                '}';
    }
}
