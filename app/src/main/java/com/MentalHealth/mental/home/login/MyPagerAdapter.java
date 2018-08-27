package com.MentalHealth.mental.home.login;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MentalHealth.mental.home.login.view.LoginFragment;
import com.MentalHealth.mental.home.login.view.ShowOneIntroFragment;
import com.MentalHealth.mental.home.login.view.ShowThreeIntroFragment;
import com.MentalHealth.mental.home.login.view.ShowTwoIntroFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {

        return 4;
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
            case 3:
                return new LoginFragment();
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
