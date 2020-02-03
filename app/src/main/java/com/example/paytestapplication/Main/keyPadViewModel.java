package com.example.paytestapplication.Main;

import androidx.lifecycle.ViewModel;

import java.text.DecimalFormat;

public class keyPadViewModel extends ViewModel {


    private String value;

    public keyPadViewModel(){
        this.value = "0,00";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        value = String.format("%03d", value);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        String finalNumber = decimalFormat.format(Double.parseDouble(value)/100);
        this.value = finalNumber.replace(".", ",");
    }
}
