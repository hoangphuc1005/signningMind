package com.MentalHealth.mental.monthinfo.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.monthinfo.model.DayDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SlidingMonthAdapter extends PagerAdapter {
    private TextView tvTitle;
    private ImageView imgBackground;
    private ImageView imgBackVideo;
    private Context context;
    private List<DayDetail> list;
    private LayoutInflater mInflator;

    public SlidingMonthAdapter(List<DayDetail> list, Context context) {
        this.list = list;
        this.context = context;
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public Object instantiateItem(ViewGroup collection, final int position) {
        ViewGroup layout;
        layout = (ViewGroup) mInflator.inflate(R.layout.custom_month_detail, collection, false);
        // RelativeLayout viewLeft = (RelativeLayout) layout.findViewById(R.id.relayPost);
        final DayDetail fixture = list.get(position);
        tvTitle = (TextView) layout.findViewById(R.id.tv_title);
        imgBackground = (ImageView) layout.findViewById(R.id.imgBackground);
        imgBackVideo = (ImageView) layout.findViewById(R.id.img_video_month);

        addFixture(fixture);
        collection.addView(layout);
        return layout;
    }

    public void updateAnswers(List<DayDetail> itemsList) {
        list = itemsList;
        notifyDataSetChanged();
    }

    private void addFixture(final DayDetail mentalHelpModel) {
        if (mentalHelpModel.getText() != null) {
            tvTitle.setVisibility(View.VISIBLE);
            SpannableString content = new SpannableString(Html.fromHtml(mentalHelpModel.getText()));
            tvTitle.setText(content);
        }
        if (mentalHelpModel.getImage() != null) {
            imgBackground.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(Constant.URL_IMAGE + mentalHelpModel.getImage())
                    .placeholder(R.drawable.ic_tram_cam)
                    .fit().centerInside()
                    .into(imgBackground);
        }
        if (mentalHelpModel.getVideo() != null) {
            imgBackVideo.setVisibility(View.VISIBLE);
            imgBackVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mentalHelpModel.getVideo()));
                    context.startActivity(browserIntent);
                }
            });
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

}
