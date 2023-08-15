package com.example.posreports.Model;

public class EmployModel {
    private  String USERNO;
    private  String USERNAME;

    public EmployModel(String USERNO, String USERNAME) {
        this.USERNO = USERNO;
        this.USERNAME = USERNAME;
    }

    public EmployModel() {
    }

    public String getUSERNO() {
        return USERNO;
    }

    public void setUSERNO(String USERNO) {
        this.USERNO = USERNO;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
}
