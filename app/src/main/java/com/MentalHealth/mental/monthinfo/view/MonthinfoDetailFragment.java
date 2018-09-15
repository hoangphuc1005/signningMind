package com.MentalHealth.mental.monthinfo.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.home.login.model.LoginModel;
import com.MentalHealth.mental.menu.PagerAdapterMenu;
import com.MentalHealth.mental.monthinfo.model.DayDetail;
import com.MentalHealth.mental.monthinfo.model.MonthDetailModel;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MentalHealth.mental.home.view.MainFragment.USER_ID;

public class MonthinfoDetailFragment extends BaseFragment {
    private SOService mService;
    public static final String MY_PREFERENCE = "Account";
    private int idMonth;
    SharedPreferences sharedpreferences;
    FragmentPagerAdapter adapterViewPager;
    ViewPager vpPager;

    @Override
    public int getLayoutId() {
        return R.layout.custom_chart_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateBackActionbar();
        actionView();

    }

    private void actionView() {
        vpPager = (ViewPager) findViewById(R.id.vpPagerSupporter);
        mService = ApiUtils.getSOService();
        Bundle bundle = getArguments();
        String position = bundle.getString("id");
        int day = bundle.getInt("day");
        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        final String userID = sharedpreferences.getString(USER_ID, "");
        if (position != null) {
            idMonth = Integer.parseInt(position);
        } else if (day > 0) {
            idMonth = day;
        }
        mService.getOneDay(String.valueOf(idMonth)).enqueue(new Callback<List<DayDetail>>() {
            @Override
            public void onResponse(Call<List<DayDetail>> call, Response<List<DayDetail>> response) {
                if (response.raw().isSuccessful()) {
                    adapterViewPager = new PagerMonthAdapter(response.body(), getChildFragmentManager());
                    vpPager.setAdapter(adapterViewPager);
                    for (DayDetail dayDetail : response.body()) {
                        mService.UpdateSateDays(userID, String.valueOf(dayDetail.getDayId())).enqueue(new Callback<LoginModel>() {
                            @Override
                            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                Log.e("===>successRespone", response.toString());
                            }

                            @Override
                            public void onFailure(Call<LoginModel> call, Throwable t) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DayDetail>> call, Throwable t) {

            }
        });
    }

    @Override
    public void updateBackActionbar() {
        super.updateBackActionbar();
    }
}
