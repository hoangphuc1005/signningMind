package com.MentalHealth.mental.menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.Data;
import com.MentalHealth.mental.infonew.view.DBInformNew;
import com.MentalHealth.mental.infonew.view.InfoNewAdapter;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;

import java.util.ArrayList;


public class FavoriteFragment extends BaseFragment implements InfoNewAdapter.OnClickRecycleView,
        SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<Data> listInfoNew;
    private InfoNewAdapter infoAdapter;
    private SwipeRefreshLayout swipeRefreshInfo;
    private TextView tvTitleFavorite;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_infonew;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setTitleActionBar("Tin Tức");
        handleBackPress();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();


    }

    private void init() {
        tvTitleFavorite = (TextView) findViewById(R.id.tvTitleFavorite);
        swipeRefreshInfo = (SwipeRefreshLayout) findViewById(R.id.swipe_refreshInfo);
        swipeRefreshInfo.setOnRefreshListener(this);
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        listInfoNew = new ArrayList<>();
        final DBInformNew dbInformNew = new DBInformNew(getContext());
        if (dbInformNew.getAllUsers().size()>0) {
            listInfoNew = (ArrayList<Data>) dbInformNew.getAllUsers();
            infoAdapter = new InfoNewAdapter(getContext(), listInfoNew, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerInfo.setLayoutManager(mLayoutManager);
            recyclerInfo.setAdapter(infoAdapter);
        } else {
            swipeRefreshInfo.setVisibility(View.GONE);
            tvTitleFavorite.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void setOnItemClick(Data position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putSerializable("InfoNew", position);
        fragment = new InfoNewDetailFragment();
        onMoveParentFragments(fragment, bundle);

    }

    @Override
    public void onRefresh() {
        swipeRefreshInfo.setRefreshing(false);
    }
}
