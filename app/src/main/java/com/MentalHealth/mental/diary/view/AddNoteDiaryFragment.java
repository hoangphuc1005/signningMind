package com.MentalHealth.mental.diary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.base.Utils;
import com.MentalHealth.mental.dbdiary.DBHistory;
import com.MentalHealth.mental.diary.model.DiaryModel;

public class AddNoteDiaryFragment extends BaseFragment {
    private EditText tvTitleDiary;
    private EditText tvContentDiary;
    private Button btnSaveDiary;
    private DiaryModel diaryModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_note_diary;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        actionView();
        comeBackHomeScreen();

    }

    private void actionView() {
        diaryModel = new DiaryModel();
        final DBHistory dbHistory = new DBHistory(getActivity());
        btnSaveDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvContentDiary.getText() != null && tvTitleDiary.getText() != null) {
                    diaryModel.setTitleOfDiary(tvTitleDiary.getText().toString());
                    diaryModel.setContentOfDiary(tvContentDiary.getText().toString());
                    diaryModel.setDateOfDiary(Utils.getCurrentDay());
                    diaryModel.setMonthOfDiary(Utils.getTime());
                    if (dbHistory.getDiary(diaryModel.getTitleOfDiary()) == null)
                        dbHistory.addDiary(diaryModel);
                    else
                        dbHistory.updateDiary(diaryModel);
                    final Bundle bundle = new Bundle();
                    onMoveFragmentMain(new MainNoteDiaryFragment(), bundle);
                    getFragmentManager().popBackStack();
                }
            }
        });

    }

    private void initView() {
        tvContentDiary = (EditText) findViewById(R.id.editContentDiary);
        tvTitleDiary = (EditText) findViewById(R.id.editTitleDiary);
        btnSaveDiary = (Button) findViewById(R.id.btnSavedDiary);
    }
}
