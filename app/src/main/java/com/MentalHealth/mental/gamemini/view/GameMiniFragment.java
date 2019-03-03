package com.MentalHealth.mental.gamemini.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.gamemini.model.LevelDataQuiz;
import com.MentalHealth.mental.gamemini.model.LevelQuiz;
import com.MentalHealth.mental.home.view.FragmentHome;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GameMiniFragment extends BaseFragment implements QuizLevelAdapter.OnClickRecycleView,
        SwipeRefreshLayout.OnRefreshListener {
    private RelativeLayout bgMainQuiz;
    private ImageView imgBack;
    private ArrayList<LevelDataQuiz> listInfoNew;
    private SOService mService;
    private QuizLevelAdapter infoAdapter;
    private SwipeRefreshLayout swipeRefreshInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_quiz;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUserVisibleHint(true);
        setTitleActionBar("Mini Game");
        updateHomeActionbar();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();
        handleBackPress();
        initView();
    }

    private void initView() {
        mService = ApiUtils.getSOService();
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        listInfoNew = new ArrayList<>();
        imgBack = (ImageView) findViewById(R.id.imgBack);
        bgMainQuiz = (RelativeLayout) findViewById(R.id.bgMainQuiz);
        addDataInfo();
        infoAdapter = new QuizLevelAdapter(getContext(), listInfoNew, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerInfo.setLayoutManager(mLayoutManager);
        recyclerInfo.setAdapter(infoAdapter);
        actionView();
    }

    private void actionView() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMoveFragmentMain(new FragmentHome(), new Bundle());

            }
        });
    }

    private void addDataInfo() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Xin đợi trong giây lát....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        getDataInfo(progressDoalog);

    }

    private void getDataInfo(final ProgressDialog progressDialog) {
        mService.getAllLevel().enqueue(new Callback<LevelQuiz>() {
            @Override
            public void onResponse(Call<LevelQuiz> call, Response<LevelQuiz> response) {
                if (response != null) {
                    bgMainQuiz.setVisibility(View.VISIBLE);
                    infoAdapter.updateAnswers(response.body().getData());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LevelQuiz> call, Throwable t) {

            }
        });
    }

    @Override
    public void setOnItemClick(int position,int levelID) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt("quiz_cho", position);
        bundle.putInt("quiz_level", levelID);
        fragment = new MiniGameAnswerFragment();
        onMoveParentFragments(fragment, bundle);

    }

    @Override
    public void onRefresh() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        getDataInfo(progressDoalog);
        swipeRefreshInfo.setRefreshing(false);
    }


}

