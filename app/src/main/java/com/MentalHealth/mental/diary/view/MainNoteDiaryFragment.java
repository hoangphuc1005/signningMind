package com.MentalHealth.mental.diary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.dbdiary.DBHistory;
import com.MentalHealth.mental.diary.model.DiaryModel;
import com.MentalHealth.mental.diary.view.DiaryNoteAdapter.OnClickRecycleView;

import java.util.ArrayList;

public class MainNoteDiaryFragment extends BaseFragment implements OnClickRecycleView, View.OnClickListener {
    private ArrayList<DiaryModel> listDiary;
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
    }

    private void actionView() {

    }

    private void initView() {
        RecyclerView recyclerDiary = (RecyclerView) findViewById(R.id.recyclerDiary);
        RelativeLayout rlAddDiary = (RelativeLayout) findViewById(R.id.rlAddDiary);
        RelativeLayout rlAddDateDiary = (RelativeLayout) findViewById(R.id.rlAddDateDiary);
        RelativeLayout rlAddMonthDiary = (RelativeLayout) findViewById(R.id.rlAddMonthDiary);
        listDiary = new ArrayList<>();
        final DBHistory dbHistory = new DBHistory(getActivity());
        if (dbHistory.getAllDiary() != null) {
            listDiary = (ArrayList<DiaryModel>) dbHistory.getAllDiary();
        }
        DiaryNoteAdapter adater = new DiaryNoteAdapter(getContext(), listDiary, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerDiary.setLayoutManager(mLayoutManager);
        recyclerDiary.setAdapter(adater);
        recyclerDiary.setNestedScrollingEnabled(false);
        rlAddDiary.setOnClickListener(this);
        rlAddDateDiary.setOnClickListener(this);
        rlAddMonthDiary.setOnClickListener(this);

    }

    @Override
    public void setOnItemClick(DiaryModel diaryModel) {
        onMoveParentFragments(new DiaryDetailFragment(), new Bundle());
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        Fragment fragment;
        switch (view.getId()) {
            case R.id.rlAddDiary:
                fragment = new AddNoteDiaryFragment();
                onMoveParentFragments(fragment, bundle);
        }
    }
}
