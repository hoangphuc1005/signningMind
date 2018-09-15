package com.MentalHealth.mental.diary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;

public class DiaryFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imgChatWithMonster;
    private ImageView imgChart;
    private ImageView imgDiary;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_diary;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        actionView();
        updateBackActionbarCustomBack();
        handleBackPress();
        comeBackHomeScreen();
        setTitleActionBar("Nhật Ký");
    }

    private void actionView() {
        imgChatWithMonster.setOnClickListener(this);
        imgChart.setOnClickListener(this);
        imgDiary.setOnClickListener(this);
    }

    private void initView() {
        imgDiary = (ImageView) findViewById(R.id.imgDiary);
        imgChatWithMonster = (ImageView) findViewById(R.id.imgChatWithMonster);
        imgChart = (ImageView) findViewById(R.id.imgChart);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.imgChart:
                break;
            case R.id.imgDiary:
                fragment = new MainNoteDiaryFragment();
                onMoveParentFragments(fragment, bundle);
                break;
            case R.id.imgChatWithMonster:
                fragment = new ChatWithMonsterFragment();
                onMoveParentFragments(fragment, bundle);
                break;
        }

    }
}
