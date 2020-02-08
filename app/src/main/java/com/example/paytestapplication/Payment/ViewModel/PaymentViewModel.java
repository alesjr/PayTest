package com.example.paytestapplication.Payment.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paytestapplication.Payment.Model.Payment;

import java.text.DecimalFormat;


public class PaymentViewModel extends ViewModel {

    private final MutableLiveData<Payment> paymentLiveData = new MutableLiveData<Payment>();

    public LiveData<Payment> getPayment() {
        if(paymentLiveData.getValue()==null) {
            paymentLiveData.setValue(new Payment());
        }
        return paymentLiveData;
    }

    public void setPayment(Payment payment) {
        paymentLiveData.postValue(payment);
    }

    public String removeLastValue() {
        String value = String.valueOf(getPayment().getValue().getPayValue()).replace(".", "");

        char[] ch = new char[value.length()-1];
        value.getChars(0, value.length()-1, ch,0 );

        return formatValueToDecimalString(String.copyValueOf(ch));
    }

    public String formatValueToDecimalString(String value) {
        value = value.replace(",", "");

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double valueDouble = Double.parseDouble(value)/100;

        Payment payment = getPayment().getValue();
        payment.setPayValue(valueDouble);
        paymentLiveData.postValue(payment);

        String finalNumber = decimalFormat.format(valueDouble);
        return finalNumber.replace(".", ",");
    }

}
