package com.MentalHealth.mental.sos.view;

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
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.library.model.DataDoccumentModel;
import com.MentalHealth.mental.library.model.DoccumentModel;
import com.MentalHealth.mental.library.view.DoccumentAdapter;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;
import com.MentalHealth.mental.sos.model.SOSDataModel;
import com.MentalHealth.mental.sos.model.SOSModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SosFragment extends BaseFragment implements DoccumentAdapter.OnClickRecycleView,
        SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<SOSDataModel> listInfoNew;
    private SOService mService;
    private SOSAdapter infoAdapter;
    private SwipeRefreshLayout swipeRefreshInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_infonew;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setTitleActionBar("SOS");
        updateBackActionbar();
        handleBackPress();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();


    }

    private void init() {
        mService = ApiUtils.getSOService();
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        listInfoNew = new ArrayList<>();
        addDataInfo();
        infoAdapter = new SOSAdapter(getContext(), listInfoNew, this);
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
        mService.getSOSInfo().enqueue(new Callback<SOSModel>() {
            @Override
            public void onResponse(Call<SOSModel> call, Response<SOSModel> response) {
                if (response != null) {
                    infoAdapter.updateAnswers(response.body().getData());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SOSModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void setOnItemClick(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt("sos", position);
        fragment = new SOSDetailFragment();
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
