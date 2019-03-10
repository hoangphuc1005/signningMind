package com.MentalHealth.mental.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.IntroductModel;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsFragment extends BaseFragment {
    private TextView webView;
    private SOService mService;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_us;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitleActionBar("Về chúng tôi");
        handleBackPress();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();
        initView();
        getInfoData();
    }

    public void initView() {
        webView = (TextView) findViewById(R.id.tvContentInfo);
        mService = ApiUtils.getSOService();
    }

    public void getInfoData() {
        mService.getIntroduct().enqueue(new Callback<IntroductModel>() {
            @Override
            public void onResponse(Call<IntroductModel> call, Response<IntroductModel> response) {
                if (response.isSuccessful()) {
                    SpannableString spannableString = new SpannableString(Html.fromHtml(response.body().getInfo()));
                    webView.setText(spannableString);
                }
            }

            @Override
            public void onFailure(Call<IntroductModel> call, Throwable t) {

            }
        });
    }

}
