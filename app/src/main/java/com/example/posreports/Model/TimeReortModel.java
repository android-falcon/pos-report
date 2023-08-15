package com.example.posreports.Model;

public class TimeReortModel {

    //[{"EMPNO":"1","EMPNAME":"Alaa","TDATE":"01\/01\/2018","TTIME":"10:30","TRANSKIND":"1","TRANSNAME":"Enter","DEPTNO":"1","SHIFTNO":"1","JOPTITIL":"1","NFIELD1":"1","NFIELD2":"1","CFIELD1":"1","CFIELD2":"1","CFIELD3":"1","CLOSED":"1","TDCDATE":"31\/12\/1899","EXPORTED":"1","POSNO":"2","USERNO":"1","USERNAME":"CASHIR1","PASSWORD":"","SUPERVISOR":"0","ADDITEM":"1","CHANGEITEMINFO":"0","REPRINTREC":"1","RETURNITEM":"1","ULOGGEDIN":"","ACHANGETIME":"","PASSWORDSTR":"350","POSNO_1":"-1","INACTIVE":"1","INDATE":"10\/10\/2019 07:00:54 م","CREATED_BY":"","UPDATED_BY":"","CREATION_DATE":"16\/12\/2021 03:53:08 م"}]
    private  String empNo;
    private  String empName;
    private  String tranceDate;
    private  String tranceTime;
    private  String action;
    private  String posNo;
//    private  String TRANSKIND;
//    private String TRANSNAME;
//    private String

    public TimeReortModel(String empNo, String empName, String tranceDate, String tranceTime, String action, String posNo) {
        this.empNo = empNo;
        this.empName = empName;
        this.tranceDate = tranceDate;
        this.tranceTime = tranceTime;
        this.action = action;
        this.posNo = posNo;
    }

    public TimeReortModel() {
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTranceDate() {
        return tranceDate;
    }

    public void setTranceDate(String tranceDate) {
        this.tranceDate = tranceDate;
    }

    public String getTranceTime() {
        return tranceTime;
    }

    public void setTranceTime(String tranceTime) {
        this.tranceTime = tranceTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }
}
