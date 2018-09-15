package com.MentalHealth.mental.infonew.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.infonew.model.InfoNewModel;
import com.MentalHealth.mental.library.model.DoccumentDetail;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoNewDetailFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private TextView tvTitle;
    private TextView tvHeaderContent;
    private TextView tvLastContent;
    private int position, positionInfo, positionDoc, positionMain;
    private String id;
    private RelativeLayout rlDoc, rlFavorite;
    Bundle bundle;
    private String imagePath;
    private SOService mService;
    private ImageView imgFavorite, imgInfoNewDetail;
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
        actionView();
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
        imgFavorite = (ImageView) findViewById(R.id.imgFavorite);
        rlDoc = (RelativeLayout) findViewById(R.id.rlDocument);
        rlFavorite = (RelativeLayout) findViewById(R.id.rlFavorite);

        addDataInfo();
    }

    private void actionView() {
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgFavorite.setImageResource(R.drawable.ic_favorite);
                InfoNewModel infoNewModel = new InfoNewModel();
                final DBInformNew dbInfo = new DBInformNew(getActivity());
                infoNewModel.setId(String.valueOf(position));
                infoNewModel.setTitleInfo(tvTitle.getText().toString());
                infoNewModel.setImgInfo(imagePath);

                if (dbInfo.getUser(infoNewModel.getId()) == null) {
//                    dbInfo.addUser(infoNewModel);
                }

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

        positionDoc = bundle.getInt("doccument");
        positionInfo = bundle.getInt("InfoNew");
        positionMain = bundle.getInt("sliding_menu");
        id = bundle.getString("id");
        if (positionDoc > 0) {
            setTitleActionBar("Tài Liệu");
            rlDoc.setVisibility(View.VISIBLE);
            rlFavorite.setVisibility(View.GONE);
            position = positionDoc;
            getDataDoc(progressDoalog);
        } else {
            if (positionInfo > 0) {
                setTitleActionBar("Tin tức");
                position = positionInfo;
                getDataInfo(progressDoalog);
            } else if (id != null) {
                position = Integer.parseInt(id);
                getDataInfo(progressDoalog);
            } else if (positionMain > 0) {
                position = positionMain;
                getDataInfo(progressDoalog);
            }

        }

    }

    private void getDataDoc(final ProgressDialog progressDialog) {
        mService.getDocumentDetail(String.valueOf(position)).enqueue(new Callback<DoccumentDetail>() {
            @Override
            public void onResponse(Call<DoccumentDetail> call, Response<DoccumentDetail> response) {
                if (response != null) {
                    progressDialog.dismiss();
                    SpannableString noidungspanned = new SpannableString(Html.fromHtml(response.body().getContent()));
                    SpannableString noidungspanned1 = new SpannableString(Html.fromHtml(response.body().getDescription()));
                    seTTextContent(response.body().getTitle(), noidungspanned1,
                            noidungspanned);
                    Picasso.with(getContext())
                            .load(Constant.URL_IMAGE + response.body().getImage())
                            .fit().centerInside()
                            .into(imgInfoNewDetail);
                }
            }

            @Override
            public void onFailure(Call<DoccumentDetail> call, Throwable t) {

            }
        });
    }

    private void getDataInfo(final ProgressDialog progressDialog) {
        mService.getInfoNewDetail(String.valueOf(position)).enqueue(new Callback<DoccumentDetail>() {
            @Override
            public void onResponse(Call<DoccumentDetail> call, Response<DoccumentDetail> response) {
                if (response != null) {
                    progressDialog.dismiss();
                    imagePath = response.body().getImage();
                    SpannableString noidungspanned = new SpannableString(Html.fromHtml(response.body().getContent()));
                    SpannableString noidungspanned1 = new SpannableString(Html.fromHtml(response.body().getDescription()));
                    seTTextContent(response.body().getTitle(), noidungspanned1,
                            noidungspanned);
                    Picasso.with(getContext())
                            .load(Constant.URL_IMAGE + response.body().getImage())
                            .placeholder(R.drawable.test_info)
                            .fit().centerInside()
                            .into(imgInfoNewDetail);
                }
            }

            @Override
            public void onFailure(Call<DoccumentDetail> call, Throwable t) {

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
        if (positionDoc > 0) {
            setTitleActionBar("Tài Liệu");
            position = positionDoc;
            getDataDoc(progressDoalog);
        } else {
            if (positionInfo > 0) {
                setTitleActionBar("Tin tức");
                position = positionInfo;
                getDataInfo(progressDoalog);
            }

        }
        swipeRefreshInfo.setRefreshing(false);
    }
}
