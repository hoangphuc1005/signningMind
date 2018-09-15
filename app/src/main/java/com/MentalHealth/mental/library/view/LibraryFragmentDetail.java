package com.MentalHealth.mental.library.view;

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
import com.MentalHealth.mental.infonew.view.InfoNewAdapter;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.library.model.DataDoccumentModel;
import com.MentalHealth.mental.library.model.DoccumentDetail;
import com.MentalHealth.mental.library.model.DoccumentModel;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragmentDetail extends BaseFragment implements DoccumentAdapter.OnClickRecycleView,
        SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<DataDoccumentModel> listInfoNew;
    private SOService mService;
    private DoccumentAdapter infoAdapter;
    private SwipeRefreshLayout swipeRefreshInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_infonew;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setTitleActionBar("Tài liệu");
        updateBackActionbar();
        callSOS();
        handleBackPressMain();
        comeBackHomeScreen();


    }

    private void init() {
        mService = ApiUtils.getSOService();
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        listInfoNew = new ArrayList<>();
        addDataInfo();
        infoAdapter = new DoccumentAdapter(getContext(), listInfoNew, this);
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
        mService.getAllDocument().enqueue(new Callback<DoccumentModel>() {
            @Override
            public void onResponse(Call<DoccumentModel> call, Response<DoccumentModel> response) {
                if (response != null) {
                    infoAdapter.updateAnswers(response.body().getData());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DoccumentModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void setOnItemClick(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt("doccument", position);
        fragment = new InfoNewDetailFragment();
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
