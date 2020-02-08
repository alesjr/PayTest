package com.example.paytestapplication.Payment.Model;

import java.util.Date;

public class Payment {

    private double payValue;
    private String companyName;
    private String address;
    private String payType;
    private Date datePayment;

    public Payment(){
        this.payValue = 0;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public double getPayValue() {
        return payValue;
    }

    public void setPayValue(double payValue) {
        this.payValue = payValue;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
