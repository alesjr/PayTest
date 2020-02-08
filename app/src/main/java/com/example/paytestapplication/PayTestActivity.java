package com.example.paytestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.paytestapplication.Payment.Model.Payment;
import com.example.paytestapplication.Payment.ViewModel.PaymentViewModel;
import com.example.paytestapplication.PaymentReceipt.Controller.PaymentReceiptActivity;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.gson.Gson;


public class PayTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_test_activity);

        final Context context = this;
        final PaymentViewModel paymentViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        final ProgressBar progressBar = findViewById(R.id.loading);

        paymentViewModel.getPayment().observe(this, new Observer<Payment>() {
            @Override
            public void onChanged(Payment payment) {
                if(payment.getPayType()!=null) {
                    Sprite fadingCircle = new FadingCircle();
                    progressBar.setIndeterminateDrawable(fadingCircle);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    blockUi();

                    new Thread( new Runnable() {
                        @Override
                        public void run() {
                            Gson json = new Gson();
                            String jsonPayment = json.toJson(paymentViewModel.getPayment().getValue());
                            Intent intent = new Intent(context, PaymentReceiptActivity.class);
                            intent.putExtra("PaymentViewModel", jsonPayment);
                            startActivity(intent);
                        }
                    } ).start();

                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    private void blockUi(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void releaseUi(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}
