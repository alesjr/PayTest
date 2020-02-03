package com.example.paytestapplication.Main;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import com.example.paytestapplication.R;

import java.text.DecimalFormat;
import java.util.Arrays;

public class KeyPadFragment extends Fragment implements View.OnClickListener {

    private keyPadViewModel keyPadViewModel;
    private TextView labelMoneyValue;

    public static KeyPadFragment newInstance() {
        return new KeyPadFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup fragmentView = (ViewGroup) inflater.inflate(R.layout.keypad_fragment, container, false);

        this.setClickEvent(fragmentView);
        labelMoneyValue = (TextView) fragmentView.findViewById(R.id.labelMoneyValue);

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
            char[] ch = new char[value.length()-1];
            value.getChars(0, value.length()-1, ch,0 );
            value = ch.length > 0 ? String.copyValueOf(ch) : "0";
        } else{
            Button button = (Button) v;
            value += button.getText();
        }

        keyPadViewModel.setValue(value);
        labelMoneyValue.setText(keyPadViewModel.getValue());
    }

    public void setClickEvent(ViewGroup view){
        for (int i=0; i < view.getChildCount(); i++) {
            View v = view.getChildAt(i);
            try{
                setClickEvent((ViewGroup) v);
            }catch (Exception ex){
                if(v instanceof Button){
                    v.setOnClickListener(this);
                }
            }
        }
    }

}
