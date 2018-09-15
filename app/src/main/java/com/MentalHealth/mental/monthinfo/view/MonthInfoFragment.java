package com.MentalHealth.mental.monthinfo.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.InfoNewModel;
import com.MentalHealth.mental.infonew.view.InfoNewAdapter;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.monthinfo.model.AllDayMonthModel;
import com.MentalHealth.mental.monthinfo.model.DataAlldayModel;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

import static com.MentalHealth.mental.home.view.MainFragment.USER_ID;
import static com.MentalHealth.mental.monthinfo.view.MonthinfoDetailFragment.MY_PREFERENCE;

public class MonthInfoFragment extends BaseFragment implements MonthInfoAdapter.OnClickRecycleView {
    private MonthInfoAdapter infoAdapter;
    private ArrayList<DataAlldayModel> listInfoNew;
    private RecyclerView recyclerInfo;
    SharedPreferences sharedpreferences;
    private SOService mService;

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
        mService = ApiUtils.getSOService();
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Xin đợi trong giây lát....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        getDataInfo(progressDoalog);

    }

    private void getDataInfo(final ProgressDialog progressDialog) {
        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        final String userID = sharedpreferences.getString(USER_ID, "");
        mService.getAllDay(userID).enqueue(new Callback<AllDayMonthModel>() {
            @Override
            public void onResponse(retrofit2.Call<AllDayMonthModel> call, Response<AllDayMonthModel> response) {
                if (response != null) {
                    infoAdapter.updateAnswers(response.body().getData());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<AllDayMonthModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void setOnItemClick(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putInt("day", position);
        fragment = new MonthinfoDetailFragment();
        onMoveParentFragments(fragment, bundle);
    }
}
