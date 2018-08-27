package com.MentalHealth.mental.infonew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.InfoNewModel;

import java.util.ArrayList;

public class InfoNewFragment extends BaseFragment implements InfoNewAdapter.OnClickRecycleView {
    private ArrayList<InfoNewModel> listInfoNew;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_infonew;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setTitleActionBar("Tin Tức");
        updateBackActionbar();
        callSOS();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();
        handleBackPress();


    }

    private void init() {
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        listInfoNew = new ArrayList<>();
        addDataInfo();
        InfoNewAdapter infoAdapter = new InfoNewAdapter(getContext(), listInfoNew, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerInfo.setLayoutManager(mLayoutManager);
        recyclerInfo.setAdapter(infoAdapter);
    }

    private void addDataInfo() {
        listInfoNew.add(new InfoNewModel(getContext().getString(R.string.dummy_title_info_1), R.drawable.test_info));
        listInfoNew.add(new InfoNewModel(getContext().getString(R.string.dummy_title_info_2), R.drawable.test_info));
        listInfoNew.add(new InfoNewModel(getContext().getString(R.string.dummy_title_info_3), R.drawable.test_info));


    }

    @Override
    public void setOnItemClick(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt("InfoNew", position);
        fragment = new InfoNewDetailFragment();
        onMoveParentFragments(fragment, bundle);

    }
}
