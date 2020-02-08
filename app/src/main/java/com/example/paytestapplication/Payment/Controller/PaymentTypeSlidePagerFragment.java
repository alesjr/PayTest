package com.example.paytestapplication.Payment.Controller;

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

import com.example.paytestapplication.R;

public class PaymentTypeSlidePagerFragment extends Fragment {

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

        return slidePagerFragment;
    }

    private class ScreenSlideTypePaymentAdapter extends FragmentStatePagerAdapter {
        public ScreenSlideTypePaymentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 1){
                return new PageFragment(R.layout.payment_type_slide_pager_two);
            }

            return new PageFragment(R.layout.payment_type_slide_pager_one);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }



}
