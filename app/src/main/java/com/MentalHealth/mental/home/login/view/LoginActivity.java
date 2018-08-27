package com.MentalHealth.mental.home.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.MentalHealth.mental.MainActivity;
import com.MentalHealth.mental.R;
import com.MentalHealth.mental.home.login.MyPagerAdapter;
import com.rd.PageIndicatorView;

public class LoginActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    ViewPager vpPager;
    PageIndicatorView pageIndicatorView;
    public static final String MY_PREFERENCE = "Account";
    public static final String USERNAME = "userNameKey";
    SharedPreferences sharedpreferences;
    public static final String PASSWORD = "passWordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signing_min);
        moveView();
        initView();
        actionView();
    }

    private void moveView() {
        sharedpreferences = getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(USERNAME)) {
            if (!sharedpreferences.getString(USERNAME, "").isEmpty()) {
                String userName = sharedpreferences.getString(USERNAME, "");
                String passWord = sharedpreferences.getString(PASSWORD, "");
                if (userName.equals("thanhtn") && passWord.equals("123456")) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }

    }

    private void initView() {
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(4); // specify total count of indicators

    }

    private void actionView() {
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

                pageIndicatorView.setSelection(position);
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here

            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }
}
