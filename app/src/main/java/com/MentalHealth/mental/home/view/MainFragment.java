package com.MentalHealth.mental.home.view;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MentalHealth.mental.MainActivity;
import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.gamemini.view.GameMiniFragment;
import com.MentalHealth.mental.menu.AboutUsFragment;
import com.MentalHealth.mental.servicefcm.notifi.view.LisNotificationFragment;


public class MainFragment extends BaseFragment implements NavDrawerFragment.FragmentDrawerListener {
    private NavDrawerFragment drawerFragment;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    public static final String USER_ID = "userID";
    private int mDefault_tab = 0;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initActionBar();
//        displayView(mDefault_tab);
        Fragment fragmentHome = null;
        Bundle bundle = new Bundle();
        fragmentHome = new FragmentHome();
        onMoveFragmentMain(fragmentHome, bundle);


    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initActionBar() {

        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayouts);
        drawerFragment = (NavDrawerFragment) getChildFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, mDrawerLayout, mToolbar);
        drawerFragment.setDrawerListener(this);

        mDrawerLayout.closeDrawer(Gravity.LEFT);

        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    }

    private void displayView(int position) {
        Fragment fragmentHome = null;
        Bundle bundle = new Bundle();
        switch (position) {

            case 0:
                fragmentHome = new AboutUsFragment();
                onMoveFragmentMain(fragmentHome, bundle);
                break;
            case 1:
                Fragment fragmentHome1 = new ChartGraphViewFragment();
                onMoveFragmentMain(fragmentHome1, bundle);
                break;
            case 2:
                fragmentHome = new LisNotificationFragment();
                onMoveFragmentMain(fragmentHome, bundle);
                break;
            case 3:
                fragmentHome = new LisNotificationFragment();
                onMoveFragmentMain(fragmentHome, bundle);
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}