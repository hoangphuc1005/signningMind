package com.MentalHealth.mental.az.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.az.model.AZChildModel;
import com.MentalHealth.mental.az.model.AZGroupModel;
import com.MentalHealth.mental.az.model.AZModelDetail;
import com.MentalHealth.mental.az.model.DataAZModel;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AZDetailFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView imgAZ;
    private ArrayList<AZGroupModel> listGroup;
    private AZGroupModel modelGroup;
    private AZChildModel modelChild;
    private ArrayList<AZChildModel> listChildModels;
    private ArrayList<AZChildModel> listChildModels1;
    private ArrayList<AZChildModel> listChildModels2;
    private ArrayList<AZChildModel> listChildModels3;
    private ArrayList<AZChildModel> listChildModels4;
    private ArrayList<AZChildModel> listChildModels5;
    private ArrayList<AZChildModel> listChildModels6;
    private ExpandableListView expandableAZ;
    private AZDetailAdapter adapter;
    private String[] tvItems;
    private SwipeRefreshLayout swipeRefreshInfo;
    private ArrayList<AZModelDetail> listInfoNew;
    private SOService mService;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_a_z_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        updateBackActionbar();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void actionView() {
        adapter = new AZDetailAdapter(getContext(), listGroup);
        expandableAZ.setAdapter(adapter);
    }

    private void initView() {
        mService = ApiUtils.getSOService();
        imgAZ = (ImageView) findViewById(R.id.img_a_z_detail);
        tvItems = getResources().getStringArray(R.array.a_z_detail);
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.sw_refreshAZ);
        swipeRefreshInfo.setOnRefreshListener(this);
        listChildModels = new ArrayList<>();
        listChildModels1 = new ArrayList<>();
        listChildModels2 = new ArrayList<>();
        listChildModels3 = new ArrayList<>();
        listChildModels4 = new ArrayList<>();
        listChildModels5 = new ArrayList<>();
        listChildModels6 = new ArrayList<>();
        listGroup = new ArrayList<>();
        checkData();
        expandableAZ = (ExpandableListView) findViewById(R.id.expanded_a_z_Detail);
    }

    private void checkData() {
        Bundle bundle = getArguments();
        int style = bundle.getInt("a_z");
        if (style == 1) {
            imgAZ.setImageResource(R.drawable.img_rlla);
            setTitleActionBar("Rối loạn lo âu");
        }
        if (style == 2) {
            imgAZ.setImageResource(R.drawable.img_anhtotramcam);
            setTitleActionBar("Trầm cảm");
        }

        if (style == 3) {
            imgAZ.setImageResource(R.drawable.img_rlcx);
            setTitleActionBar("Rối loạn lưỡng cực");
        }
        mService.getAZDetail(String.valueOf(style)).enqueue(new Callback<List<AZModelDetail>>() {
            @Override
            public void onResponse(Call<List<AZModelDetail>> call, Response<List<AZModelDetail>> response) {
                if (response != null) {
                    for (AZModelDetail modelDetail : response.body()) {
                        listChildModels.add(new AZChildModel(modelDetail.getDefinition()));
                        modelGroup = new AZGroupModel("Khái Niệm?", listChildModels);
                        listGroup.add(modelGroup);
                        listChildModels1.add(new AZChildModel(modelDetail.getSymptom()));
                        modelGroup = new AZGroupModel("Triệu chứng của bệnh", listChildModels1);
                        listGroup.add(modelGroup);
                        listChildModels2.add(new AZChildModel(modelDetail.getType()));
                        modelGroup = new AZGroupModel("Phân Loại ", listChildModels2);
                        listGroup.add(modelGroup);
                        listChildModels3.add(new AZChildModel(modelDetail.getTreatments()));
                        modelGroup = new AZGroupModel("Trị Liệu", listChildModels3);
                        listGroup.add(modelGroup);
                        listChildModels4.add(new AZChildModel(modelDetail.getHelp()));
                        modelGroup = new AZGroupModel("Tự trợ giúp", listChildModels4);
                        listGroup.add(modelGroup);
                        listChildModels5.add(new AZChildModel(modelDetail.getQuote()));
                        modelGroup = new AZGroupModel("Trích nguồn", listChildModels5);
                        listGroup.add(modelGroup);
                        expandableAZ = (ExpandableListView) findViewById(R.id.expanded_a_z_Detail);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            expandableAZ.setNestedScrollingEnabled(false);
//                        }
                        actionView();
                    }


                }
            }

            @Override
            public void onFailure(Call<List<AZModelDetail>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshInfo.setRefreshing(false);
    }
}

