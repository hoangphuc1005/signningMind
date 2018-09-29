package com.MentalHealth.mental.infonew.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.infonew.model.Data;
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
    private Data data;
    private RelativeLayout rlDoc, rlFavorite;
    Bundle bundle;
    private String imagePath;
    private SOService mService;
    private ImageView imgFavorite;
    private WebView imgInfoNewDetail;
    private SwipeRefreshLayout swipeRefreshInfo;
    private boolean paused = false;
    public static final String MY_PREFERENCE = "Account";
    public static final String CHECK_FAVORITE = "CHECK";
    SharedPreferences sharedpreferences;

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
        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        mService = ApiUtils.getSOService();
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        bundle = getArguments();
        tvTitle = (TextView) findViewById(R.id.tvTitleInfoNewDetail);
        tvHeaderContent = (TextView) findViewById(R.id.tvTitleHeaderInfoNewDetail);
        tvLastContent = (TextView) findViewById(R.id.tvLastContentInfoNewDetail);
        imgInfoNewDetail = (WebView) findViewById(R.id.imgInfoNewDetail);
        imgFavorite = (ImageView) findViewById(R.id.imgFavorite);
        rlDoc = (RelativeLayout) findViewById(R.id.rlDocument);
        rlFavorite = (RelativeLayout) findViewById(R.id.rlFavorite);
        addDataInfo();
        final DBInformNew dbInfo = new DBInformNew(getActivity());
        if (data != null) {
            if (dbInfo.getUser(String.valueOf(data.getId())) != null) {
                if (data.getTitle().equals(dbInfo.getUser(String.valueOf(data.getId())).getTitle())) {
                    imgFavorite.setImageResource(R.drawable.ic_heart_android);
                }
            }
        }


    }

    private void actionView() {
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DBInformNew dbInfo = new DBInformNew(getActivity());
                paused = !paused;
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (paused == true) {
                    imgFavorite.setImageResource(R.drawable.ic_thich);
                    dbInfo.deleteUserById(data);
                    editor.putString(CHECK_FAVORITE, "check");
                } else {
                    imgFavorite.setImageResource(R.drawable.ic_heart_android);
                    editor.putString(CHECK_FAVORITE, "unCheck");
                    if (dbInfo.getUser(String.valueOf(data.getId())) == null) {
                        dbInfo.addUser(data);
                    } else {
                        dbInfo.updateUser(data);
                    }
                }
//                imgFavorite.setImageResource(R.drawable.ic_favorite);


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
        id = bundle.getString("id");
        positionDoc = bundle.getInt("doccument");
        data = (Data) bundle.getSerializable("InfoNew");
        positionMain = bundle.getInt("sliding_menu");
        if (positionDoc > 0) {
            setTitleActionBar("Tài Liệu");
            rlDoc.setVisibility(View.VISIBLE);
            rlFavorite.setVisibility(View.GONE);
            position = positionDoc;
            getDataDoc(progressDoalog);
        } else {
            if (id != null) {
                position = Integer.parseInt(id);
                getDataInfo(progressDoalog);
            } else if (positionMain > 0) {
                position = positionMain;
                getDataInfo(progressDoalog);
            } else if (data != null) {
                position = data.getId();
                getDataInfo(progressDoalog);
            }

        }

    }

    private void getDataDoc(final ProgressDialog progressDialog) {
        mService.getDocumentDetail(String.valueOf(position)).enqueue(new Callback<DoccumentDetail>() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onResponse(Call<DoccumentDetail> call, Response<DoccumentDetail> response) {
                if (response != null) {
                    progressDialog.dismiss();
                    SpannableString noidungspanned = new SpannableString(Html.fromHtml(response.body().getContent()));
                    SpannableString noidungspanned1 = new SpannableString(Html.fromHtml(response.body().getDescription()));
                    seTTextContent(response.body().getTitle(), noidungspanned1,
                            noidungspanned);
                    imgInfoNewDetail.getSettings().setJavaScriptEnabled(true);
                    imgInfoNewDetail.loadUrl(Constant.URL_IMAGE + response.body().getImage());
                }
            }

            @Override
            public void onFailure(Call<DoccumentDetail> call, Throwable t) {

            }
        });
    }

    private void getDataInfo(final ProgressDialog progressDialog) {
        mService.getInfoNewDetail(String.valueOf(position)).enqueue(new Callback<DoccumentDetail>() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onResponse(Call<DoccumentDetail> call, Response<DoccumentDetail> response) {
                if (response != null) {
                    progressDialog.dismiss();
                    imagePath = response.body().getImage();
                    SpannableString noidungspanned = new SpannableString(Html.fromHtml(response.body().getContent()));
                    SpannableString noidungspanned1 = new SpannableString(Html.fromHtml(response.body().getDescription()));
                    seTTextContent(response.body().getTitle(), noidungspanned1,
                            noidungspanned);
                    imgInfoNewDetail.getSettings().setJavaScriptEnabled(true);
                    imgInfoNewDetail.loadUrl(Constant.URL_IMAGE + response.body().getImage());
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
