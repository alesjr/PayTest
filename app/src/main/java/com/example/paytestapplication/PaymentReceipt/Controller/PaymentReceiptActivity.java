package com.example.paytestapplication.PaymentReceipt.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paytestapplication.Payment.Model.Payment;
import com.example.paytestapplication.R;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_receipt_activity);

        int width = 0;
        int height = 0;
        Bitmap bitmap;
        Canvas canvas;
        Window window;
        Intent intent = this.getIntent();
        Gson json = new Gson();
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale ("pt", "BR"));
        Payment payment = json.fromJson(intent.getStringExtra("PaymentViewModel"), Payment.class);

        FrameLayout rootReceipt = findViewById(R.id.receipt);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View receiptInformation = layoutInflater.inflate(R.layout.payment_receipt_information, null);

        ((TextView) receiptInformation.findViewById(R.id.labelPaymentType)).setText(payment.getPayType());
        ((TextView) receiptInformation.findViewById(R.id.labelPaymentDate)).setText(sdf.format(new Date()));
        ((TextView) receiptInformation.findViewById(R.id.labelPaymentValue)).setText(currencyFormatter.format(payment.getPayValue()));

        width = rootReceipt.getLayoutParams().width;
        height = rootReceipt.getLayoutParams().height;

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        receiptInformation.draw(canvas);

        rootReceipt.addView(receiptInformation);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorTurquoise));
    }
}
