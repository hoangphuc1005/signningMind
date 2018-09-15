package com.MentalHealth.mental.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MentalHealth.mental.home.login.view.LoginFragment;
import com.MentalHealth.mental.home.login.view.ShowOneIntroFragment;
import com.MentalHealth.mental.home.login.view.ShowThreeIntroFragment;
import com.MentalHealth.mental.home.login.view.ShowTwoIntroFragment;
import com.MentalHealth.mental.monthinfo.model.DayDetail;

public class PagerAdapterMenu extends FragmentPagerAdapter{


    public PagerAdapterMenu(FragmentManager fragmentManager) {
        super(fragmentManager);
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
                    return new ShowOneIntroFragment();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return new ShowTwoIntroFragment();
                case 2: // Fragment # 1 - This will show SecondFragment
                    return new ShowThreeIntroFragment();
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
