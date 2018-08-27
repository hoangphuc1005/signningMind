package com.MentalHealth.mental.monthinfo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.InfoNewModel;
import com.MentalHealth.mental.infonew.view.InfoNewAdapter;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;

import java.util.ArrayList;

public class MonthInfoFragment extends BaseFragment implements MonthInfoAdapter.OnClickRecycleView {
    private MonthInfoAdapter infoAdapter;
    private ArrayList<InfoNewModel> listInfoNew;
    private RecyclerView recyclerInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_month_info;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setTitleActionBar("30 Ngày");
        updateBackActionbar();
        callSOS();
        updateBackActionbarCustomBack();
        handleBackPress();
        comeBackHomeScreen();
    }

    private void initView() {
        recyclerInfo = (RecyclerView) findViewById(R.id.recyclerMonthInfo);
        listInfoNew = new ArrayList<>();
        addDataInfo();
        infoAdapter = new MonthInfoAdapter(getContext(), listInfoNew, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerInfo.setLayoutManager(mLayoutManager);
        recyclerInfo.setAdapter(infoAdapter);
    }

    private void addDataInfo() {
        for (int i = 1; i <= 30; i++) {
            if (i <= 3) {
                listInfoNew.add(new InfoNewModel("Ngày " + i, R.drawable.img_ngaydaxem));
            } else if (i > 3 && i <= 4) {
                listInfoNew.add(new InfoNewModel("Ngày " + i, R.drawable.img_ngaychuaxem));
            } else
                listInfoNew.add(new InfoNewModel("Ngày " + i, R.drawable.img_ngaybikhoa));
        }
    }

    @Override
    public void setOnItemClick(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putInt("day", position+1);
        fragment = new InfoNewDetailFragment();
        onMoveParentFragments(fragment, bundle);
    }
}
