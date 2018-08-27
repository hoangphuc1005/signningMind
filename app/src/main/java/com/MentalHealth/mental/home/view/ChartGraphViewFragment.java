package com.MentalHealth.mental.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.home.model.Item;
import com.MentalHealth.mental.home.model.SOAnswersResponse;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;
import com.MentalHealth.mental.home.view.AnswersAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ChartGraphViewFragment extends BaseFragment implements AnswersAdapter.PostItemListener {
    private RecyclerView recyclerViewData;
    private AnswersAdapter mAdapter;
    private SOService mService;

    @Override
    public int getLayoutId() {
        return R.layout.custom_chart_view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUserVisibleHint(true);
        updateHomeActionbar();
        initChargeView();
    }

    private void initChargeView() {
        mService = ApiUtils.getSOService();
        recyclerViewData = (RecyclerView) findViewById(R.id.recycler_data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewData.setLayoutManager(mLayoutManager);
        mAdapter = new AnswersAdapter(getContext(), new ArrayList<Item>(0), this);
        recyclerViewData.setAdapter(mAdapter);
        recyclerViewData.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerViewData.addItemDecoration(itemDecoration);
    }




    @Override
    public void onPostClick(long id) {

    }

}
