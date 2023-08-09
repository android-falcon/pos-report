package com.example.posreports.Model;

public class TransactionModel {
    private  String TRANSKIND;
    private  String POSN;
    private  String VHFDATE;
    private  String VHFTIM;
    private  String VHFI;
    private  String ITEMOCODE;
    private  String ITEMONAMEA;
    private  String PRICE;
    private  String PQTY;
    private  String TOTAL;
    private  String NETBEFORDIS;
    private  String NETAFTERDIS;
    private  String TAXABLE;
    private  String AVGCOSTPRICE;
    private  String USERNO;
    private  String ITEMG;
    private  String TAXAMT;
    private  String DISCOUNT;
    private  String IOQTY;
    private  String PRICEKIND;
    private  String OFFERNO;
    private  String OFFERNAME;
    private  String ISKIT;
    private  String COSTUMERNO;
    private  String COSTUMERNAME;
    private  String CONO;


    public TransactionModel(String TRANSKIND, String POSN, String VHFDATE, String VHFTIM, String VHFI, String ITEMOCODE, String ITEMONAMEA, String PRICE, String PQTY, String TOTAL, String NETBEFORDIS, String NETAFTERDIS, String TAXABLE, String AVGCOSTPRICE, String USERNO, String ITEMG, String TAXAMT, String DISCOUNT, String IOQTY, String PRICEKIND, String OFFERNO, String OFFERNAME, String ISKIT, String COSTUMERNO, String COSTUMERNAME, String CONO) {
        this.TRANSKIND = TRANSKIND;
        this.POSN = POSN;
        this.VHFDATE = VHFDATE;
        this.VHFTIM = VHFTIM;
        this.VHFI = VHFI;
        this.ITEMOCODE = ITEMOCODE;
        this.ITEMONAMEA = ITEMONAMEA;
        this.PRICE = PRICE;
        this.PQTY = PQTY;
        this.TOTAL = TOTAL;
        this.NETBEFORDIS = NETBEFORDIS;
        this.NETAFTERDIS = NETAFTERDIS;
        this.TAXABLE = TAXABLE;
        this.AVGCOSTPRICE = AVGCOSTPRICE;
        this.USERNO = USERNO;
        this.ITEMG = ITEMG;
        this.TAXAMT = TAXAMT;
        this.DISCOUNT = DISCOUNT;
        this.IOQTY = IOQTY;
        this.PRICEKIND = PRICEKIND;
        this.OFFERNO = OFFERNO;
        this.OFFERNAME = OFFERNAME;
        this.ISKIT = ISKIT;
        this.COSTUMERNO = COSTUMERNO;
        this.COSTUMERNAME = COSTUMERNAME;
        this.CONO = CONO;
    }

    public TransactionModel() {


    }

    public String getTRANSKIND() {
        return TRANSKIND;
    }

    public void setTRANSKIND(String TRANSKIND) {
        this.TRANSKIND = TRANSKIND;
    }

    public String getPOSN() {
        return POSN;
    }

    public void setPOSN(String POSN) {
        this.POSN = POSN;
    }

    public String getVHFDATE() {
        return VHFDATE;
    }

    public void setVHFDATE(String VHFDATE) {
        this.VHFDATE = VHFDATE;
    }

    public String getVHFTIM() {
        return VHFTIM;
    }

    public void setVHFTIM(String VHFTIM) {
        this.VHFTIM = VHFTIM;
    }

    public String getVHFI() {
        return VHFI;
    }

    public void setVHFI(String VHFI) {
        this.VHFI = VHFI;
    }

    public String getITEMOCODE() {
        return ITEMOCODE;
    }

    public void setITEMOCODE(String ITEMOCODE) {
        this.ITEMOCODE = ITEMOCODE;
    }

    public String getITEMONAMEA() {
        return ITEMONAMEA;
    }

    public void setITEMONAMEA(String ITEMONAMEA) {
        this.ITEMONAMEA = ITEMONAMEA;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getPQTY() {
        return PQTY;
    }

    public void setPQTY(String PQTY) {
        this.PQTY = PQTY;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getNETBEFORDIS() {
        return NETBEFORDIS;
    }

    public void setNETBEFORDIS(String NETBEFORDIS) {
        this.NETBEFORDIS = NETBEFORDIS;
    }

    public String getNETAFTERDIS() {
        return NETAFTERDIS;
    }

    public void setNETAFTERDIS(String NETAFTERDIS) {
        this.NETAFTERDIS = NETAFTERDIS;
    }

    public String getTAXABLE() {
        return TAXABLE;
    }

    public void setTAXABLE(String TAXABLE) {
        this.TAXABLE = TAXABLE;
    }

    public String getAVGCOSTPRICE() {
        return AVGCOSTPRICE;
    }

    public void setAVGCOSTPRICE(String AVGCOSTPRICE) {
        this.AVGCOSTPRICE = AVGCOSTPRICE;
    }

    public String getUSERNO() {
        return USERNO;
    }

    public void setUSERNO(String USERNO) {
        this.USERNO = USERNO;
    }

    public String getITEMG() {
        return ITEMG;
    }

    public void setITEMG(String ITEMG) {
        this.ITEMG = ITEMG;
    }

    public String getTAXAMT() {
        return TAXAMT;
    }

    public void setTAXAMT(String TAXAMT) {
        this.TAXAMT = TAXAMT;
    }

    public String getDISCOUNT() {
        return DISCOUNT;
    }

    public void setDISCOUNT(String DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }

    public String getIOQTY() {
        return IOQTY;
    }

    public void setIOQTY(String IOQTY) {
        this.IOQTY = IOQTY;
    }

    public String getPRICEKIND() {
        return PRICEKIND;
    }

    public void setPRICEKIND(String PRICEKIND) {
        this.PRICEKIND = PRICEKIND;
    }

    public String getOFFERNO() {
        return OFFERNO;
    }

    public void setOFFERNO(String OFFERNO) {
        this.OFFERNO = OFFERNO;
    }

    public String getOFFERNAME() {
        return OFFERNAME;
    }

    public void setOFFERNAME(String OFFERNAME) {
        this.OFFERNAME = OFFERNAME;
    }

    public String getISKIT() {
        return ISKIT;
    }

    public void setISKIT(String ISKIT) {
        this.ISKIT = ISKIT;
    }

    public String getCOSTUMERNO() {
        return COSTUMERNO;
    }

    public void setCOSTUMERNO(String COSTUMERNO) {
        this.COSTUMERNO = COSTUMERNO;
    }

    public String getCOSTUMERNAME() {
        return COSTUMERNAME;
    }

    public void setCOSTUMERNAME(String COSTUMERNAME) {
        this.COSTUMERNAME = COSTUMERNAME;
    }

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }
}
