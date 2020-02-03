package com.example.paytestapplication.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.example.paytestapplication.R;

public class PayActivity extends AppCompatActivity {


    private Button buttonOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        getSupportActionBar().hide();
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.pay_activity);

    }

}
