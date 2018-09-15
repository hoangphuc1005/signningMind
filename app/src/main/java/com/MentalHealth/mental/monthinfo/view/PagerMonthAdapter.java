package com.MentalHealth.mental.monthinfo.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MentalHealth.mental.home.login.view.ShowOneIntroFragment;
import com.MentalHealth.mental.home.login.view.ShowThreeIntroFragment;
import com.MentalHealth.mental.home.login.view.ShowTwoIntroFragment;
import com.MentalHealth.mental.monthinfo.model.DayDetail;

import java.util.List;

public class PagerMonthAdapter extends FragmentPagerAdapter {
    private List<DayDetail> dayDetail;

    public PagerMonthAdapter(List<DayDetail> dayDetail, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.dayDetail = dayDetail;
    }

    // Returns total number of pages
    @Override
    public int getCount() {

        return 3;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                if (dayDetail.size()>=1) {
                    return new DayDetailSlideFragment(dayDetail.get(0));
                } else
                    return new DayDetailSlideFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                if (dayDetail.size()>=2 ){
                    return new DayDetailTwoFragment(dayDetail.get(1));
                } else {
                    return new DayDetailTwoFragment();
                }
            case 2: // Fragment # 1 - This will show SecondFragment
                if (dayDetail.size()>=3) {
                    return new DayDetailThreeFragment(dayDetail.get(2));
                } else return new DayDetailThreeFragment();

            default:
                return null;
        }
    }


    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
