package com.example.paytestapplication.PaymentReceipt.ViewModel;

import android.media.Image;

import androidx.lifecycle.ViewModel;

import java.util.Date;

public class PaymentReceiptViewModel extends ViewModel {

    private double value;
    private String endereco;
    private String company;
    private String typePayment;
    private Date dateTimePayment;
    private Image logo;

}
