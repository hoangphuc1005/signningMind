package com.MentalHealth.mental.az.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.az.model.AZModel;
import com.MentalHealth.mental.az.model.DataAZModel;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.library.model.DataDoccumentModel;
import com.MentalHealth.mental.library.model.DoccumentModel;
import com.MentalHealth.mental.library.view.DoccumentAdapter;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AZFragment extends BaseFragment implements AZAdapter.OnClickRecycleView,
        SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<DataAZModel> listInfoNew;
    private SOService mService;
    private AZAdapter infoAdapter;
    private SwipeRefreshLayout swipeRefreshInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_infonew;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setTitleActionBar("Từ A đến Z");
        updateBackActionbar();
        updateBackActionbarCustomBack();
        handleBackPress();
        comeBackHomeScreen();
    }

    private void initView() {
        mService = ApiUtils.getSOService();
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        listInfoNew = new ArrayList<>();
        addDataInfo();
        infoAdapter = new AZAdapter(getContext(), listInfoNew, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerInfo.setLayoutManager(mLayoutManager);
        recyclerInfo.setAdapter(infoAdapter);
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
        mService.getAllAZ().enqueue(new Callback<AZModel>() {
            @Override
            public void onResponse(Call<AZModel> call, Response<AZModel> response) {
                if (response != null) {
                    infoAdapter.updateAnswers(response.body().getData());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AZModel> call, Throwable t) {

            }
        });
    }
    @Override
    public void setOnItemClick(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt("a_z", position);
        fragment = new AZDetailFragment();
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

