package com.example.paytestapplication.PaymentReceipt.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.paytestapplication.Payment.Model.Payment;
import com.example.paytestapplication.Payment.ViewModel.PaymentViewModel;
import com.example.paytestapplication.R;
import com.google.gson.Gson;

public class PaymentReceiptActivity extends AppCompatActivity {

    private Bitmap bitmapReceipt;
    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_receipt_activity);

//        drawable = getApplicationContext().getResources().getDrawable(R.drawable.recibo_background);
//        bitmapReceipt = ((BitmapDrawable)drawable).getBitmap();
//        Drawable d = new BitmapDrawable(bitmapReceipt, R.drawable.recibo_background);

        Intent intent = this.getIntent();
        Gson json = new Gson();

        final PaymentViewModel paymentViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        paymentViewModel.setPayment(json.fromJson(intent.getStringExtra("PaymentViewModel"), Payment.class));


    }
}
