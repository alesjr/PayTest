package com.example.paytestapplication.PaymentType.ViewModel;

import androidx.lifecycle.ViewModel;

import java.text.DecimalFormat;
import java.util.Date;

public class keyPadViewModel extends ViewModel {

    private String value;

    public keyPadViewModel(){
        this.value = "0,00";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        String finalNumber = decimalFormat.format(Double.parseDouble(value)/100);
        this.value = finalNumber.replace(".", ",");
    }

    public String removeLastValue(String value){
        char[] ch = new char[value.length()-1];
        value.getChars(0, value.length()-1, ch,0 );
        return String.copyValueOf(ch);
    }
}
