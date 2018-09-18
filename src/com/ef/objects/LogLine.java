package com.ef.objects;

public class LogLine {
    private String date;
    private String ip;
    private String rquest;
    private String status;
    private String userAgent;

    public LogLine(String date, String ip, String rquest, String status, String userAgent) {
        this.date = date;
        this.ip = ip;
        this.rquest = rquest;
        this.status = status;
        this.userAgent = userAgent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRquest() {
        return rquest;
    }

    public void setRquest(String rquest) {
        this.rquest = rquest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
