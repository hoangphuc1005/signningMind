package com.MentalHealth.mental.infonew.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.Data;
import com.MentalHealth.mental.infonew.model.InfoNew;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoNewFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<Data> listInfoNew;
    private SOService mService;
    private InfoNewAdapter infoAdapter;
    private SwipeRefreshLayout swipeRefreshInfo;
    private String page;
    private LinearLayoutManager layoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int mCountpage = 1;
    private String[] countPage;
    private int tmpTotalPage = 0;
    private int firstVisibleItems;
    private RecyclerView recyclerInfo;

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
        mService = ApiUtils.getSOService();
        layoutManager = new LinearLayoutManager(getActivity());
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        listInfoNew = new ArrayList<>();
        final DBInformNew dbInformNew = new DBInformNew(getContext());
        addDataInfo();
        nextPage();

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
        mService.getInfoNew().enqueue(new Callback<InfoNew>() {
            @Override
            public void onResponse(Call<InfoNew> call, Response<InfoNew> response) {
                if (response != null) {
                    listInfoNew = (ArrayList<Data>) response.body().getData();
                    infoAdapter = new InfoNewAdapter(getContext(), listInfoNew, itemClick);
                    recyclerInfo.setLayoutManager(layoutManager);
                    recyclerInfo.setAdapter(infoAdapter);
                    if (response.body().getNextPageUrl() != null) {
                        page = response.body().getNextPageUrl();

                    }
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InfoNew> call, Throwable t) {

            }
        });
    }

    private void nextPage() {
        recyclerInfo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                if (page!=null){
                    countPage=page.split("=");
                    firstVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if ((firstVisibleItems + visibleItemCount) >= totalItemCount &&
                            tmpTotalPage != totalItemCount) {
                        if (firstVisibleItems + visibleItemCount == totalItemCount && totalItemCount != 0 &&
                                mCountpage < Integer.parseInt(countPage[1]))
                            mCountpage = Integer.parseInt(countPage[1]);
                        mService.getInfoNewPage(String.valueOf(mCountpage)).enqueue(new Callback<InfoNew>() {
                            @Override
                            public void onResponse(Call<InfoNew> call, Response<InfoNew> response) {
                                listInfoNew.addAll(response.body().getData());
                                infoAdapter.updateAnswers(listInfoNew);
                            }

                            @Override
                            public void onFailure(Call<InfoNew> call, Throwable t) {

                            }
                        });
                        mCountpage++;
                    }
                }


            }
        });
    }

    InfoNewAdapter.OnClickRecycleView itemClick = new InfoNewAdapter.OnClickRecycleView() {
        @Override
        public void setOnItemClick(Data position) {
            Fragment fragment;
            Bundle bundle = new Bundle();
            bundle.putSerializable("InfoNew", position);
            fragment = new InfoNewDetailFragment();
            onMoveParentFragments(fragment, bundle);

        }
    };

    @Override
    public void onRefresh() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        getDataInfo(progressDoalog);
        swipeRefreshInfo.setRefreshing(false);
    }
}
