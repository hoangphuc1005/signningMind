package com.MentalHealth.mental.diary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

    private LinearLayout lnWorked;
    private LinearLayout lnRelax;
    private LinearLayout lnFriend;
    private LinearLayout lnLoved;
    private LinearLayout lnSported;
    private LinearLayout lnParty;

    private LinearLayout lnMovie;
    private LinearLayout lnReadBook;
    private LinearLayout lnPlayGame;
    private LinearLayout lnSuccess;

    private RelativeLayout rlAddTextSuccess;
    private View groupWorked;
    private RelativeLayout rlFeel;
    private RelativeLayout rlDid;
    private RelativeLayout rlAddText;
    private TextView tvFeeling;
    private TextView tvDoWork;
    private TextView tvDoAddText;
    ImageView imgSuccess;

    private EditText editTextFinal;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat_with_monster_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        actionView();
//        comeBackHomeScreen();
        updateBackActionbar();
//        setTitleActionBar("Chat với Monster");

    }

    private void initView() {
        lnGreat = (LinearLayout) findViewById(R.id.lnGreatChat);
        lnNormal = (LinearLayout) findViewById(R.id.lnNormalChat);
        lnGood = (LinearLayout) findViewById(R.id.lnGoodChat);
        lnNotGood = (LinearLayout) findViewById(R.id.lnBadChat);
        lnBad = (LinearLayout) findViewById(R.id.lnTerrible);
        lnGroupFeeling = (LinearLayout) findViewById(R.id.lnGroupFeeling);

        lnWorked = (LinearLayout) findViewById(R.id.lnWorked);
        lnRelax = (LinearLayout) findViewById(R.id.lnRelax);
        lnFriend = (LinearLayout) findViewById(R.id.lnFriend);
        lnLoved = (LinearLayout) findViewById(R.id.lnLoved);
        lnMovie = (LinearLayout) findViewById(R.id.lnMovie);
        lnReadBook = (LinearLayout) findViewById(R.id.lnReadBook);

        lnSported = (LinearLayout) findViewById(R.id.lnSported);
        lnParty = (LinearLayout) findViewById(R.id.lnParty);
        lnPlayGame = (LinearLayout) findViewById(R.id.lnPlayGame);
        lnSuccess = (LinearLayout) findViewById(R.id.lnSuccess);


        groupWorked = findViewById(R.id.groupWorked);
        rlAddText = (RelativeLayout) findViewById(R.id.rlAddText);
        rlFeel = (RelativeLayout) findViewById(R.id.rlHeaderChat);
        rlDid = (RelativeLayout) findViewById(R.id.rlContentChat);
        tvDoAddText = (TextView) findViewById(R.id.tvDoAddText);
        tvFeeling = (TextView) findViewById(R.id.tvFelling);
        tvDoWork = (TextView) findViewById(R.id.tvDoWork);
        rlAddTextSuccess = (RelativeLayout) findViewById(R.id.rlAddTextSuccess);
        editTextFinal = (EditText) findViewById(R.id.editFinal);
        imgSuccess = (ImageView) findViewById(R.id.imgSuccess);


    }

    private void actionView() {
        lnGreat.setOnClickListener(this);
        lnNotGood.setOnClickListener(this);
        lnNormal.setOnClickListener(this);
        lnBad.setOnClickListener(this);
        lnGood.setOnClickListener(this);
        lnWorked.setOnClickListener(this);
        lnRelax.setOnClickListener(this);
        lnFriend.setOnClickListener(this);
        lnLoved.setOnClickListener(this);
        lnMovie.setOnClickListener(this);
        lnReadBook.setOnClickListener(this);

        lnSported.setOnClickListener(this);
        lnParty.setOnClickListener(this);
        lnPlayGame.setOnClickListener(this);
        lnSuccess.setOnClickListener(this);
        imgSuccess.setOnClickListener(this);

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

            case R.id.lnWorked:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Làm việc" + " " + tvDoWork.getText());
                lnWorked.setEnabled(false);
                break;
            case R.id.lnRelax:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Thư giãn" + " " + tvDoWork.getText());
                lnRelax.setEnabled(false);
                break;
            case R.id.lnFriend:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Bạn bè" + " " + tvDoWork.getText());
                lnFriend.setEnabled(false);
                break;
            case R.id.lnLoved:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Hẹn hò" + " " + tvDoWork.getText());
                lnLoved.setEnabled(false);
                break;
            case R.id.lnSported:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Thể thao" + " " + tvDoWork.getText());
                lnSported.setEnabled(false);
                break;

            case R.id.lnParty:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Tiệc tùng" + " " + tvDoWork.getText());
                lnParty.setEnabled(false);
                break;
            case R.id.lnMovie:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Xem phim" + " " + tvDoWork.getText());
                lnMovie.setEnabled(false);
                break;
            case R.id.lnReadBook:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Đọc sách" + " " + tvDoWork.getText());
                lnReadBook.setEnabled(false);
                break;
            case R.id.lnPlayGame:
                tvDoWork.setVisibility(View.VISIBLE);
                tvDoWork.setText("Chơi game" + " " + tvDoWork.getText());
                lnPlayGame.setEnabled(false);
                break;
            case R.id.lnSuccess:
                tvDoWork.setVisibility(View.VISIBLE);
                groupWorked.setVisibility(View.GONE);
                rlAddText.setVisibility(View.VISIBLE);
                rlAddTextSuccess.setVisibility(View.VISIBLE);
                break;
            case R.id.imgSuccess:
                tvDoAddText.setText(editTextFinal.getText());
                tvDoAddText.setVisibility(View.VISIBLE);
                rlAddTextSuccess.setVisibility(View.GONE);


        }
    }

    private void showChatWorked() {
        rlDid.setVisibility(View.VISIBLE);
        lnGroupFeeling.setVisibility(View.GONE);
        groupWorked.setVisibility(View.VISIBLE);

    }
}
