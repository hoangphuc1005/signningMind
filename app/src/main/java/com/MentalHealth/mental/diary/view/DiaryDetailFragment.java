package com.MentalHealth.mental.diary.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.dbdiary.DBHistory;
import com.MentalHealth.mental.diary.model.DiaryModel;

public class DiaryDetailFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvContentDiary, tvTitleDiaryDetail, texContentDiaryDetail;
    private ImageView imgDelete;
    private DiaryModel model;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_diary_ditail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        model = (DiaryModel) getArguments().getSerializable("diaryModel");
        texContentDiaryDetail = (TextView) findViewById(R.id.texContentDiaryDetail);
        tvContentDiary = (TextView) findViewById(R.id.tvContentDiary);
        tvTitleDiaryDetail = (TextView) findViewById(R.id.tvTitleDiaryDetail);
        imgDelete = (ImageView) findViewById(R.id.imgDeleteDairyDetail);
        imgDelete.setOnClickListener(this);
        if (model != null) {
            tvTitleDiaryDetail.setText(model.getTitleOfDiary());
            tvContentDiary.setText(model.getDateOfDiary() + "-" + model.getMonthOfDiary());
            texContentDiaryDetail.setText(model.getContentOfDiary());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgDeleteDairyDetail:
                DBHistory dbHistory = new DBHistory(getActivity());
                dbHistory.deleteDiary(model);
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
        }
    }
}
