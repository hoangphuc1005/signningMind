package com.MentalHealth.mental.sos.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.library.model.DoccumentDetail;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;
import com.MentalHealth.mental.sos.model.SOSModelDetail;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SOSDetailFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private TextView tvTitle;
    private TextView tvHeaderContent;
    private TextView tvLastContent;
    private int position;
    Bundle bundle;
    private SOService mService;
    private ImageView imageContent, imgInfoNewDetail;
    private SwipeRefreshLayout swipeRefreshInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_info_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateBackActionbar();
        initView();
//        handleBackPressMain();
    }

    private void initView() {
        mService = ApiUtils.getSOService();
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        bundle = getArguments();
        tvTitle = (TextView) findViewById(R.id.tvTitleInfoNewDetail);
        tvHeaderContent = (TextView) findViewById(R.id.tvTitleHeaderInfoNewDetail);
        tvLastContent = (TextView) findViewById(R.id.tvLastContentInfoNewDetail);
        imgInfoNewDetail = (ImageView) findViewById(R.id.imgInfoNewDetail);

        addDataInfo();
    }


    private void addDataInfo() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Xin đợi trong giây lát....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        position = bundle.getInt("sos");
        setTitleActionBar("SOS");
        getDataDoc(progressDoalog);

    }

    private void getDataDoc(final ProgressDialog progressDialog) {
        mService.getSOSDetail(String.valueOf(position)).enqueue(new Callback<SOSModelDetail>() {
            @Override
            public void onResponse(Call<SOSModelDetail> call, Response<SOSModelDetail> response) {
                if (response != null) {
                    progressDialog.dismiss();
                    SpannableString noidungspanned = new SpannableString(Html.fromHtml(response.body().getContent()));
                    SpannableString noidungspanned1 = new SpannableString(Html.fromHtml(response.body().getDescription()));
                    seTTextContent(response.body().getTitle(), noidungspanned1,
                            noidungspanned);
                    Picasso.with(getContext())
                            .load(Constant.URL_IMAGE + response.body().getImage())
                            .placeholder(R.drawable.test_info)
                            .fit().centerCrop()
                            .into(imgInfoNewDetail);
                }
            }

            @Override
            public void onFailure(Call<SOSModelDetail> call, Throwable t) {

            }
        });
    }


    private void seTTextContent(String title, SpannableString header, SpannableString lastContent) {
        tvTitle.setText(title);
        tvHeaderContent.setText(header);
        tvLastContent.setText(lastContent);

    }

    @Override
    public void onRefresh() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        getDataDoc(progressDoalog);
        swipeRefreshInfo.setRefreshing(false);
    }
}
