package com.example.posreports.Model;

public class SalesManModel {
    //SALESNO":"7302","SALESNAME":"MOH'D ABIALHIJA","ADDRESS":"","COUNTRY":"","CITY":"",
    // "POBOX":"","ZIPCODE":"","EMAIL":"","TEL":"","MOBILE":"","DISCOUNTPER":"0",
    // "VDate":"04\/07\/2021","SALESMANPASSWORD":"","POSNO":"-1","INDATE":"04\/07\/2021 11:41:39 ص",
    // "INACTIVE":"0","COMM":"0"

    private String SALESNO;
    private String SALESNAME;
    private String ADDRESS;
    private String COUNTRY;
    private String CITY;
    private String POBOX;
    private String ZIPCODE;
    private String EMAIL;
    private String TEL;
    private String MOBILE;
    private String DISCOUNTPER;
    private String SALESMANPASSWORD;
    private String POSNO;
    private String VDate;
    private String INDATE;
    private String INACTIVE;
    private String COMM;

    public SalesManModel(String SALESNO, String SALESNAME, String ADDRESS, String COUNTRY, String CITY, String POBOX, String ZIPCODE, String EMAIL, String TEL, String MOBILE, String DISCOUNTPER, String SALESMANPASSWORD, String POSNO, String VDate, String INDATE, String INACTIVE, String COMM) {
        this.SALESNO = SALESNO;
        this.SALESNAME = SALESNAME;
        this.ADDRESS = ADDRESS;
        this.COUNTRY = COUNTRY;
        this.CITY = CITY;
        this.POBOX = POBOX;
        this.ZIPCODE = ZIPCODE;
        this.EMAIL = EMAIL;
        this.TEL = TEL;
        this.MOBILE = MOBILE;
        this.DISCOUNTPER = DISCOUNTPER;
        this.SALESMANPASSWORD = SALESMANPASSWORD;
        this.POSNO = POSNO;
        this.VDate = VDate;
        this.INDATE = INDATE;
        this.INACTIVE = INACTIVE;
        this.COMM = COMM;
    }

    public SalesManModel() {
    }

    public String getSALESNO() {
        return SALESNO;
    }

    public void setSALESNO(String SALESNO) {
        this.SALESNO = SALESNO;
    }

    public String getSALESNAME() {
        return SALESNAME;
    }

    public void setSALESNAME(String SALESNAME) {
        this.SALESNAME = SALESNAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getPOBOX() {
        return POBOX;
    }

    public void setPOBOX(String POBOX) {
        this.POBOX = POBOX;
    }

    public String getZIPCODE() {
        return ZIPCODE;
    }

    public void setZIPCODE(String ZIPCODE) {
        this.ZIPCODE = ZIPCODE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getDISCOUNTPER() {
        return DISCOUNTPER;
    }

    public void setDISCOUNTPER(String DISCOUNTPER) {
        this.DISCOUNTPER = DISCOUNTPER;
    }

    public String getSALESMANPASSWORD() {
        return SALESMANPASSWORD;
    }

    public void setSALESMANPASSWORD(String SALESMANPASSWORD) {
        this.SALESMANPASSWORD = SALESMANPASSWORD;
    }

    public String getPOSNO() {
        return POSNO;
    }

    public void setPOSNO(String POSNO) {
        this.POSNO = POSNO;
    }

    public String getVDate() {
        return VDate;
    }

    public void setVDate(String VDate) {
        this.VDate = VDate;
    }

    public String getINDATE() {
        return INDATE;
    }

    public void setINDATE(String INDATE) {
        this.INDATE = INDATE;
    }

    public String getINACTIVE() {
        return INACTIVE;
    }

    public void setINACTIVE(String INACTIVE) {
        this.INACTIVE = INACTIVE;
    }

    public String getCOMM() {
        return COMM;
    }

    public void setCOMM(String COMM) {
        this.COMM = COMM;
    }
}
