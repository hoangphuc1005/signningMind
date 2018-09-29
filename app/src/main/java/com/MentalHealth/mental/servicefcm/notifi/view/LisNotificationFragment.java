package com.MentalHealth.mental.servicefcm.notifi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.diary.view.ChatWithMonsterFragment;
import com.MentalHealth.mental.gamemini.view.MiniGameAnswerFragment;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.monthinfo.view.DayDetailSlideFragment;
import com.MentalHealth.mental.servicefcm.model.DBNotification;
import com.MentalHealth.mental.servicefcm.model.NotificationModel;
import com.MentalHealth.mental.servicefcm.notifi.model.DBCountNot;
import com.MentalHealth.mental.servicefcm.notifi.model.NotModel;

import java.util.ArrayList;

public class LisNotificationFragment extends BaseFragment implements NotificationListAdapter.OnClickRecycleView {
    private ArrayList<NotificationModel> notificationModelList;
    private RecyclerView recyclerViewNotification;
    private NotificationListAdapter adapter;
    private TextView tvShowNotification;
    private String idNotification;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_notification;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setTitleActionBar("Thông báo");
        callSOS();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();
        handleBackPress();

    }

    private void initView() {

        final DBCountNot dbcountnot = new DBCountNot(getContext());
        dbcountnot.deleteAllUser();
        tvShowNotification = (TextView) findViewById(R.id.tvShowNotification);
        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_notifi);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewNotification.setLayoutManager(mLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewNotification.getContext(),
                linearLayoutManager.getOrientation());
        recyclerViewNotification.addItemDecoration(dividerItemDecoration);
        notificationModelList = new ArrayList<>();
        DBNotification dbNotification = new DBNotification(getContext());
        if (dbNotification.getAllUsers().size() > 0) {
            notificationModelList = (ArrayList<NotificationModel>) dbNotification.getAllUsers();
            adapter = new NotificationListAdapter(getContext(), notificationModelList, this);
            recyclerViewNotification.setAdapter(adapter);
        } else tvShowNotification.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOnItemClick(String type, String id) {
        idNotification = id;
        Bundle args = new Bundle();

        args.putString("id", idNotification);
        switch (type) {
            case "0":
                onMoveParentFragments(new InfoNewDetailFragment(), args);
                break;
            case "1":
                onMoveParentFragments(new DayDetailSlideFragment(), args);
                break;
            case "2":
                onMoveParentFragments(new ChatWithMonsterFragment(), args);
                break;
            case "3":
                onMoveParentFragments(new MiniGameAnswerFragment(), args);
                break;
        }
    }

    private void moveFragment(Fragment fragment) {
//                mFragment.setArguments(getIntent().getExtras()); //old
        Bundle args = new Bundle();

        args.putString("id", idNotification);
        fragment.setArguments(args);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit();
    }
}
