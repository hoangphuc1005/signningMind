package com.MentalHealth.mental.monthinfo.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.monthinfo.model.DayDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

@SuppressLint("ValidFragment")
public class DayDetailThreeFragment extends BaseFragment

{

    private DayDetail dayDetail;
    private TextView tvContent;
    private ImageView imgView;
    private ImageView imgVideo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_day_detail;
    }


    public DayDetailThreeFragment(DayDetail dayDetail) {
        this.dayDetail = dayDetail;

    }

    public DayDetailThreeFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvContent = (TextView) findViewById(R.id.tvContentDayDetail);
        imgView = (ImageView) findViewById(R.id.imgDayDetail);
        imgVideo = (ImageView) findViewById(R.id.imgVideoDayDetail);
        checkData();
    }

    private void checkData() {
        addDataForView();
    }

    private void addDataForView() {
        if(dayDetail!=null){
            if (dayDetail.getImage() != null) {
                imgView.setVisibility(View.VISIBLE);
                Picasso.with(getContext())
                        .load(Constant.URL_IMAGE + dayDetail.getImage())
                        .fit().centerInside()
                        .into(imgView);
            } else if (dayDetail.getTitle() != null) {
                tvContent.setVisibility(View.VISIBLE);
                SpannableString noidungspanned = new SpannableString(Html.fromHtml(dayDetail.getTitle()));
                tvContent.setText(noidungspanned);
            } else if (dayDetail.getVideo() != null) {
                imgVideo.setVisibility(View.VISIBLE);
                imgVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(dayDetail.getVideo()));
                        getContext().startActivity(browserIntent);
                    }
                });

            }
        }

    }
}
