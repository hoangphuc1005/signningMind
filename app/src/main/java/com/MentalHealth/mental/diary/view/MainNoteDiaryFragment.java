package com.MentalHealth.mental.diary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.base.Utils;
import com.MentalHealth.mental.dbdiary.DBHistory;
import com.MentalHealth.mental.diary.model.DiaryModel;
import com.MentalHealth.mental.diary.view.DiaryNoteAdapter.OnClickRecycleView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class MainNoteDiaryFragment extends BaseFragment implements OnClickRecycleView, View.OnClickListener {
    private ArrayList<DiaryModel> listDiary;
    private ArrayList<DiaryModel> listDiaryDay;
    private ArrayList<DiaryModel> listDiaryYear;
    RelativeLayout rlAddDateDiary, rlAddMonthDiary;
    private DiaryNoteAdapter adater;
    RecyclerView recyclerDiary;
    private Bundle bundle;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_diary_favorite;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        actionView();
        updateBackActionbar();
    }

    private void actionView() {

    }

    private void initView() {
        recyclerDiary = (RecyclerView) findViewById(R.id.recyclerDiary);
        RelativeLayout rlAddDiary = (RelativeLayout) findViewById(R.id.rlAddDiary);
        rlAddDateDiary = (RelativeLayout) findViewById(R.id.rlAddDateDiary);
        rlAddMonthDiary = (RelativeLayout) findViewById(R.id.rlAddMonthDiary);
        listDiary = new ArrayList<>();
        listDiaryDay = new ArrayList<>();
        listDiaryYear = new ArrayList<>();
        final DBHistory dbHistory = new DBHistory(getActivity());
        if (dbHistory.getAllDiary() != null) {
            listDiary = (ArrayList<DiaryModel>) dbHistory.getAllDiary();
        }
        adater = new DiaryNoteAdapter(getContext(), listDiary, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerDiary.setLayoutManager(mLayoutManager);
        recyclerDiary.setAdapter(adater);
        recyclerDiary.setNestedScrollingEnabled(false);
        rlAddDiary.setOnClickListener(this);
        rlAddDateDiary.setOnClickListener(this);
        rlAddMonthDiary.setOnClickListener(this);
    }

    @Override
    public void setOnItemClick(DiaryModel diaryModel, Integer type) {
        switch (type) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putSerializable("diaryModel", diaryModel);
                onMoveParentFragments(new DiaryDetailFragment(), bundle);
                break;
            case 1:
                getCurrentMonth(diaryModel);
                break;
            case 2:
                adater.updateDay(listDiary,1);
                break;


        }

    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        Fragment fragment;
        switch (view.getId()) {
            case R.id.rlAddDiary:
                fragment = new AddNoteDiaryFragment();
                onMoveParentFragments(fragment, bundle);
            case R.id.rlAddDateDiary:
                getCurrentDate();
                break;
            case R.id.rlAddMonthDiary:
                getCurrentYear();
                break;
        }
    }

    private void getCurrentDate() {
        listDiaryDay.clear();
        for (DiaryModel diary : listDiary) {
            String day = Utils.getCurrentDay();
            if (day.equals(diary.getDateOfDiary())) {
                listDiaryDay.add(diary);
                adater.updateDay(listDiaryDay, 1);
            }
        }
    }

    private void getCurrentMonth(DiaryModel diary) {
        listDiaryYear.clear();
        String test1 = "";
        for (DiaryModel diaryModel : listDiary) {
            if (diary.getMonthOfYear().equals(diaryModel.getMonthOfYear())) {
                if (!test1.equals(diary.getMonthOfYear())) {
                    test1 = diary.getMonthOfYear();
                    listDiaryYear.add(diary);
                    adater.updateDay(listDiaryYear, 3);
                }


            }
        }

    }

    private void getCurrentYear() {
        listDiaryYear.clear();
        String test1 = "";
        for (DiaryModel diary : listDiary) {
                if (!test1.equals(diary.getYearOfDiary())) {
                    test1 = diary.getYearOfDiary();
                    listDiaryYear.add(diary);
                    adater.updateDay(listDiaryYear, 2);
                }



        }

    }
}
