package com.MentalHealth.mental.sos.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;

public class SosFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_sos;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitleActionBar("SOS");
        updateBackActionbarCustomBack();
        handleBackPress();
    }
}
