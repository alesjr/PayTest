package com.example.paytestapplication.PaymentType.Controller;

import androidx.lifecycle.ViewModelProviders;

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

import com.example.paytestapplication.PaymentType.ViewModel.keyPadViewModel;
import com.example.paytestapplication.R;

public class KeyPadFragment extends Fragment implements View.OnClickListener {

    private com.example.paytestapplication.PaymentType.ViewModel.keyPadViewModel keyPadViewModel;
    private TextView labelMoneyValue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup fragmentView = (ViewGroup) inflater.inflate(R.layout.keypad_fragment, container, false);

        this.setClickEvent(fragmentView);
        labelMoneyValue = fragmentView.findViewById(R.id.labelMoneyValue);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.keyPadViewModel = ViewModelProviders.of(this).get(keyPadViewModel.class);
    }

    @Override
    public void onClick(View v) {
        String value = keyPadViewModel.getValue().replace(",", "");
        if(v.getId() == R.id.buttonBackspace){
            value = keyPadViewModel.removeLastValue(value);
        } else{
            Button button = (Button) v;
            value += button.getText();
        }

        keyPadViewModel.setValue(value);
        labelMoneyValue.setText(keyPadViewModel.getValue());
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
