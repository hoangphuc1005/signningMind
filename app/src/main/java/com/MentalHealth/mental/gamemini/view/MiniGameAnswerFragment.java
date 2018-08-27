package com.MentalHealth.mental.gamemini.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.gamemini.model.AnswerModel;

import java.util.ArrayList;

public class MiniGameAnswerFragment extends BaseFragment {
    private ArrayList<AnswerModel> listInfoNew;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mini_game;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        updateBackActionbar();
        callSOS();
        handleBackPressMain();
    }

    private void init() {
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recyclerAnswer);
        listInfoNew = new ArrayList<>();
        addDataInfo();
        MiniGameAnswerAdapter infoAdapter = new MiniGameAnswerAdapter(getContext(), listInfoNew);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerInfo.setLayoutManager(mLayoutManager);
        recyclerInfo.setAdapter(infoAdapter);
    }

    private void addDataInfo() {
        listInfoNew.add(new AnswerModel("Việt Nam",
                R.drawable.img_a));
        listInfoNew.add(new AnswerModel("Trung Quốc",
                R.drawable.img_b));
        listInfoNew.add(new AnswerModel("Mỹ",
                R.drawable.img_c));
        listInfoNew.add(new AnswerModel("CamPuChia",
                R.drawable.img_d));


    }

}
