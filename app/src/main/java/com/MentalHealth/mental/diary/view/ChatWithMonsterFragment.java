package com.MentalHealth.mental.diary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;

/**
 * Created by hoangphuc on 18/07/2018.
 */
public class ChatWithMonsterFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout lnGreat;
    private LinearLayout lnGood;
    private LinearLayout lnNormal;
    private LinearLayout lnNotGood;
    private LinearLayout lnBad;
    private LinearLayout lnGroupFeeling;
    private View groupWorked;
    private RelativeLayout rlFeel;
    private RelativeLayout rlDid;
    private RelativeLayout rlAddText;
    private TextView tvFeeling;
    private TextView tvDoWork;
    private TextView tvDoAddText;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat_with_monster_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        actionView();

    }

    private void initView() {
        lnGreat = (LinearLayout) findViewById(R.id.lnGreatChat);
        lnNormal = (LinearLayout) findViewById(R.id.lnNormalChat);
        lnGood = (LinearLayout) findViewById(R.id.lnGoodChat);
        lnNotGood = (LinearLayout) findViewById(R.id.lnBadChat);
        lnBad = (LinearLayout) findViewById(R.id.lnTerrible);
        lnGroupFeeling = (LinearLayout) findViewById(R.id.lnGroupFeeling);
        groupWorked = findViewById(R.id.groupWorked);
        rlAddText = (RelativeLayout) findViewById(R.id.rlAddText);
        rlFeel = (RelativeLayout) findViewById(R.id.rlHeaderChat);
        rlDid = (RelativeLayout) findViewById(R.id.rlContentChat);
        tvDoAddText = (TextView) findViewById(R.id.tvDoAddText);
        tvFeeling = (TextView) findViewById(R.id.tvFelling);
        tvDoWork = (TextView) findViewById(R.id.tvDoWork);
    }

    private void actionView() {
        lnGreat.setOnClickListener(this);
        lnNotGood.setOnClickListener(this);
        lnNormal.setOnClickListener(this);
        lnBad.setOnClickListener(this);
        lnGood.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lnGreatChat:
                tvFeeling.setVisibility(View.VISIBLE);
                tvFeeling.setText("Tuyệt Vời");
                showChatWorked();
                break;
            case R.id.lnGoodChat:
                tvFeeling.setVisibility(View.VISIBLE);
                tvFeeling.setText("Tốt");
                showChatWorked();
                break;
            case R.id.lnNormalChat:
                tvFeeling.setVisibility(View.VISIBLE);
                tvFeeling.setText("Bình Thường");
                showChatWorked();
                break;
            case R.id.lnBadChat:
                tvFeeling.setVisibility(View.VISIBLE);
                tvFeeling.setText("Xấu");
                showChatWorked();
                break;
            case R.id.lnTerrible:
                tvFeeling.setVisibility(View.VISIBLE);
                tvFeeling.setText("Tồi Tệ");
                showChatWorked();
                break;
        }
    }

    private void showChatWorked() {
        rlDid.setVisibility(View.VISIBLE);
        lnGroupFeeling.setVisibility(View.GONE);
        groupWorked.setVisibility(View.VISIBLE);

    }
}
