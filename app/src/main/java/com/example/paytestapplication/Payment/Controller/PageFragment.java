package com.example.paytestapplication.Payment.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.paytestapplication.Payment.Model.Payment;
import com.example.paytestapplication.Payment.ViewModel.PaymentViewModel;
import com.example.paytestapplication.R;

public class PageFragment extends Fragment implements View.OnClickListener {

    private int layout;
    private PaymentViewModel paymentViewModel;

    public PageFragment(int layout){
        this.layout = layout;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(this.layout, container, false);
        setClickEvent(root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.paymentViewModel = new ViewModelProvider(requireActivity()).get(PaymentViewModel.class);
    }

    @Override
    public void onClick(View v) {
        String paymentType = "";
        switch (v.getId()){
            case R.id.buttonCredit:{
                paymentType = "Crédito";
                break;
            }
            case R.id.buttonDebit:{
                paymentType = "Débito";
                break;
            }
            case R.id.buttonCupom:{
                paymentType = "Cupom";
                break;
            }
            case R.id.buttonMoney:{
                paymentType = "Dinheiro";
                break;
            }
            case R.id.buttonVr:{
                paymentType = "V.R.";
                break;
            }
        }

        Payment payment = paymentViewModel.getPayment().getValue();
        payment.setPayType(paymentType);
        paymentViewModel.setPayment(payment);
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
