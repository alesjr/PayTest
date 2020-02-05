package com.example.paytestapplication.PaymentType.Controller;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.paytestapplication.PayTestActivity;
import com.example.paytestapplication.R;

public class PaymentTypeSlidePagerFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup slidePagerFragment = (ViewGroup) inflater.inflate(R.layout.payment_type_slide_pager, container, false);

        pagerAdapter = new ScreenSlideTypePaymentAdapter(this.getChildFragmentManager());

        mPager = slidePagerFragment.findViewById(R.id.viewPager);
        mPager.setAdapter(pagerAdapter);
        mPager.addOnPageChangeListener(this);

        return slidePagerFragment;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    private class ScreenSlideTypePaymentAdapter extends FragmentStatePagerAdapter {
        public ScreenSlideTypePaymentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            System.out.println(position);
            if(position == 0){
                return new PageFragment(R.layout.payment_type_slide_pager_one);
            }
            return new PageFragment(R.layout.payment_type_slide_pager_two);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private static class PageFragment extends Fragment implements View.OnClickListener{

        int layout;

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

            AlertDialog alert = new AlertDialog.Builder(this.getContext()).create();
            alert.setTitle("Voltando no android agora");
            alert.setMessage(paymentType);
            alert.show();
        }

        private void setClickEvent(ViewGroup view){
            for (int i=0; i < view.getChildCount(); i++) {
                View v = view.getChildAt(i);
                try{
                    setClickEvent((ViewGroup) v);
                }catch (Exception ex){
                    if(v instanceof ImageButton){
                        v.setOnClickListener(this);
                    }
                }
            }
        }
    }

}
