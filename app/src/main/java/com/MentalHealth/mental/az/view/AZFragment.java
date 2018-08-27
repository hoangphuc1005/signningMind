package com.MentalHealth.mental.az.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.view.InfoNewFragment;

public class AZFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imgStress;
    private ImageView imgBoth;
    private ImageView imgWorry;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_a_z;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        actionView();
        setTitleActionBar("Từ A đến Z");
        updateBackActionbar();
        updateBackActionbarCustomBack();
        handleBackPress();
    }

    private void actionView() {
        imgWorry.setOnClickListener(this);
        imgBoth.setOnClickListener(this);
        imgStress.setOnClickListener(this);
    }

    private void initView() {
        imgStress = (ImageView) findViewById(R.id.imgStress);
        imgBoth = (ImageView) findViewById(R.id.imgBoth);
        imgWorry = (ImageView) findViewById(R.id.imgWorry);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putString("a_z","a");
        Bundle bundle1 = new Bundle();
        bundle1.putString("a_z","b");
        Bundle bundle2 = new Bundle();
        bundle2.putString("a_z","c");
        switch (view.getId()) {
            case R.id.imgStress:
                fragment = new AZDetailFragment();
                onMoveParentFragments(fragment, bundle);
                break;
            case R.id.imgBoth:
                fragment = new AZDetailFragment();
                onMoveParentFragments(fragment, bundle1);
                break;
            case R.id.imgWorry:
                fragment = new AZDetailFragment();
                onMoveParentFragments(fragment, bundle2);
                break;
        }

    }


}
