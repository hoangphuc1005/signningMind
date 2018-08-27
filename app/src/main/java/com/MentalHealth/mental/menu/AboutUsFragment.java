package com.MentalHealth.mental.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;

public class AboutUsFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_us;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitleActionBar("Ve Chung toi");
        updateBackActionbarCustomBack();
        handleBackPress();
        comeBackHomeScreen();
    }
}
