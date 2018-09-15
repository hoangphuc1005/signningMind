package com.MentalHealth.mental.servicefcm.notifi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.servicefcm.notifi.model.NotificationModel;

import java.util.ArrayList;

public class LisNotificationFragment extends BaseFragment implements NotificationListAdapter.OnClickRecycleView {
    private ArrayList<NotificationModel> notificationModelList;
    private RecyclerView recyclerViewNotification;
    private NotificationListAdapter adapter;

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
        recyclerViewNotification = (RecyclerView) findViewById(R.id.recycler_notifi);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewNotification.setLayoutManager(mLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewNotification.getContext(),
                linearLayoutManager.getOrientation());
        recyclerViewNotification.addItemDecoration(dividerItemDecoration);
        notificationModelList = new ArrayList<>();
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        notificationModelList.add(new NotificationModel("30  ngay", "hom nay ban co vui khong"));
        adapter = new NotificationListAdapter(getContext(), notificationModelList, this);
        recyclerViewNotification.setAdapter(adapter);
    }

    @Override
    public void setOnItemClick(int position) {
        onMoveParentFragments(new InfoNewDetailFragment(), new Bundle());
    }
}
