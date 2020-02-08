package com.example.paytestapplication.Payment.Controller;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.paytestapplication.Payment.ViewModel.PaymentViewModel;
import com.example.paytestapplication.R;

public class KeyPadFragment extends Fragment implements View.OnClickListener {

    private TextView labelMoneyValue;
    private PaymentViewModel paymentViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup fragmentView = (ViewGroup) inflater.inflate(R.layout.payment_keypad_fragment, container, false);

        this.setClickEvent(fragmentView);
        labelMoneyValue = fragmentView.findViewById(R.id.labelMoneyValue);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.paymentViewModel = new ViewModelProvider(requireActivity()).get(PaymentViewModel.class);
    }

    @Override
    public void onClick(View v) {
        String value = String.valueOf(labelMoneyValue.getText());
        if(v.getId() == R.id.buttonBackspace){
            value = paymentViewModel.removeLastValue();
        } else{
            Button button = (Button) v;
            value+=button.getText();
            value = paymentViewModel.formatValueToDecimalString(value);
        }

        labelMoneyValue.setText(value);
    }

    private void setClickEvent(ViewGroup view){
        for (int i=0; i < view.getChildCount(); i++) {
            View v = view.getChildAt(i);
            try{
                setClickEvent((ViewGroup) v);
            }catch (Exception ex){
                if(v instanceof Button || v instanceof ImageButton){
                    v.setOnClickListener(this);
                }
            }
        }
    }

}
