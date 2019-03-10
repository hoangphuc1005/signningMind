package com.MentalHealth.mental.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.menu.PagerAdapterMenu;


public class ChartGraphViewFragment extends BaseFragment {
    FragmentPagerAdapter adapterViewPager;
    ViewPager vpPager;

    @Override
    public int getLayoutId() {
        return R.layout.custom_chart_view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTitleActionBar("Hướng Dẫn");
        callSOS();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();
        handleBackPress();
        initChargeView();
    }

    private void initChargeView() {
        vpPager = (ViewPager) findViewById(R.id.vpPagerSupporter);
        adapterViewPager = new PagerAdapterMenu(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);

    }


}
