package com.example.posreports.Model;

public class POSModel {
    private String POSNO;

    private String POSNAME;

    public POSModel() {
    }

    public POSModel(String POSNO, String POSNAME) {
        this.POSNO = POSNO;
        this.POSNAME = POSNAME;
    }

    public String getPOSNO() {
        return POSNO;
    }

    public void setPOSNO(String POSNO) {
        this.POSNO = POSNO;
    }

    public String getPOSNAME() {
        return POSNAME;
    }

    public void setPOSNAME(String POSNAME) {
        this.POSNAME = POSNAME;
    }
}
