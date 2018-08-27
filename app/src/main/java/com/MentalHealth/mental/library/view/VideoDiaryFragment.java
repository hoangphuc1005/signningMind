package com.MentalHealth.mental.library.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.InfoNewModel;
import com.MentalHealth.mental.infonew.view.InfoNewAdapter;

import java.util.ArrayList;


public class VideoDiaryFragment extends BaseFragment implements InfoNewAdapter.OnClickRecycleView {
    private ArrayList<InfoNewModel> listInfoNew;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video_library;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setTitleActionBar("Các video trị liệu");
        updateBackActionbar();
        callSOS();
        updateBackActionbarCustomBack();
        handleBackPress();
        comeBackHomeScreen();
    }

    private void initView() {
        RecyclerView recyclerVideo = (RecyclerView) findViewById(R.id.recyclerVideoDiary);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listInfoNew = new ArrayList<>();
        addDataInfo();
        InfoNewAdapter infoAdapter = new InfoNewAdapter(getContext(), listInfoNew, this);
        recyclerVideo.setLayoutManager(mLayoutManager);
        recyclerVideo.setAdapter(infoAdapter);
    }

    @Override
    public void setOnItemClick(int position) {
        switch (position) {
            case 0:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=wENWyGTvmVM"));
                getContext().startActivity(browserIntent);
                break;
            case 1:
                Intent browserIntent1 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=lVLn4RqVh4Q"));
                getContext().startActivity(browserIntent1);
                break;
            case 2:
                Intent browserIntent2 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=gUVvkWvgBso"));
                getContext().startActivity(browserIntent2);
                break;

        }

    }


    private void addDataInfo() {
        listInfoNew.add(new InfoNewModel(getContext().getString(R.string.dummy_title_info_1),
                R.drawable.test_info));
        listInfoNew.add(new InfoNewModel(getContext().getString(R.string.dummy_title_info_1),
                R.drawable.test_info));
        listInfoNew.add(new InfoNewModel(getContext().getString(R.string.dummy_title_info_1),
                R.drawable.test_info));
    }
}
