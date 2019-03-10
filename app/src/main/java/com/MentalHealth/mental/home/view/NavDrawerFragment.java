package com.MentalHealth.mental.home.view;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.MentalHealth.mental.R;
import com.MentalHealth.mental.home.model.SlidingMenuModel;
import com.MentalHealth.mental.home.view.SlidingMenuAdapter;

import java.util.ArrayList;

import static com.MentalHealth.mental.home.view.FragmentHome.MY_PREFERENCE;


public class NavDrawerFragment extends Fragment implements View.OnClickListener {
    private View mContainerView;
    private DrawerLayout mDrawerLayout;
    private ListView grMenu;
    private ImageView imvEdit;
    private SlidingMenuAdapter slidingMenuAdapter;
    private ArrayList slidingList;
    private TypedArray imgIcons;
    private String[] tvItems;
    private View layout;
    private int count = 0;
    private FragmentDrawerListener mDrawerListener;
    private ActionBarDrawerToggle mDrawerToggle;
    public static SharedPreferences sharedpreferences;

    private int pos = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        init();
        addData();
        return layout;

    }


    public void init() {
        grMenu = (ListView) layout.findViewById(R.id.lvSdingMenu);
        tvItems = getResources().getStringArray(R.array.sliding_menu_tv);
        imgIcons = getResources().obtainTypedArray(R.array.sliding_menu_img_icons);
        imvEdit = (ImageView) layout.findViewById(R.id.imvEdit);
        imvEdit.setOnClickListener(this);

    }


    public void setDrawerListener(FragmentDrawerListener listener) {
        this.mDrawerListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imvEdit) {
//            startActivity(new Intent(getActivity(), UpdateUserInforActivity.class));
        }
    }

    @Override
    public void onDestroy() {
        Log.e("onDestroy", "onDestroy");
        super.onDestroy();
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = (DrawerLayout) drawerLayout.findViewById(R.id.drawerLayouts);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };
//
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }

    public void addData() {
        slidingList = new ArrayList();
        slidingList.clear();
        for (int i = 0; i < tvItems.length; i++) {
            slidingList.add(new SlidingMenuModel(imgIcons.getResourceId(i, -1), tvItems[i]));
        }
        imgIcons.recycle();
        slidingMenuAdapter = new SlidingMenuAdapter(getActivity(), slidingList);
        grMenu.setAdapter(slidingMenuAdapter);
        grMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isSignOut = getString(R.string.txt_sign_out).equals(tvItems[position]);
                if (isSignOut == false) {

                    mDrawerListener.onDrawerItemSelected(view, position);

                    if (position < (tvItems.length - 1)) {
                        slidingMenuAdapter.setPosition(tvItems[position]);
                        pos = position;
                    }
                }
                mDrawerLayout.closeDrawer(mContainerView);
            }
        });

    }


}


