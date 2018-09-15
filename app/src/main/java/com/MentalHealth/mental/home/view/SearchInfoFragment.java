package com.MentalHealth.mental.home.view;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.model.Data;
import com.MentalHealth.mental.infonew.model.InfoNew;
import com.MentalHealth.mental.infonew.view.InfoNewAdapter;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInfoFragment extends BaseFragment implements InfoNewAdapter.OnClickRecycleView {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private ArrayList<Data> listInfoNew;
    private SOService mService;
    private InfoNewAdapter infoAdapter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        handleBackPress();
        updateBackActionbarCustomBack();
        iniView();
        hideToolBar();
    }

    private void iniView() {
        mService = ApiUtils.getSOService();
        listInfoNew = new ArrayList<>();
        RecyclerView recyclerInfo = (RecyclerView) findViewById(R.id.recycler_info);
        addDataInfo();
        infoAdapter = new InfoNewAdapter(getContext(), listInfoNew, this);
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
        mService.getInfoNew().enqueue(new Callback<InfoNew>() {
            @Override
            public void onResponse(Call<InfoNew> call, Response<InfoNew> response) {
                if (response != null) {
                    infoAdapter.updateAnswers(response.body().getData());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InfoNew> call, Throwable t) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_infomation;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        }
        if (searchView != null) {
            search(searchView);
        }
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                infoAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setOnItemClick(int position) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        bundle.putInt("InfoNew", position);
        fragment = new InfoNewDetailFragment();
        onMoveParentFragments(fragment, bundle);
        showToolBar();

    }

}
