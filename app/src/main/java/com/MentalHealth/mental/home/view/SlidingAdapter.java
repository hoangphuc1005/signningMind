package com.MentalHealth.mental.home.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.MentalHealth.mental.R;
import com.MentalHealth.mental.home.model.MentalHelpModel;

import java.util.ArrayList;
import java.util.List;


public class SlidingAdapter extends PagerAdapter {
    private TextView tvTitle;
    private TextView tvDesc;
    private List<MentalHelpModel> list;
    private LayoutInflater mInflator;
    private ArrayList<Integer> lstBg;
    private FixtureItemClick mFixtureItemClick;

    public SlidingAdapter(List<MentalHelpModel> list, Context context, ArrayList<Integer> listBg, FixtureItemClick fixtureItemClick) {
        this.list = list;
        this.lstBg = listBg;
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFixtureItemClick = fixtureItemClick;
    }

    public interface FixtureItemClick {
        void onClickFixture(int position);
    }

    public Object instantiateItem(ViewGroup collection, final int position) {
        ViewGroup layout;
        layout = (ViewGroup) mInflator.inflate(R.layout.custom_fixture_detail, collection, false);
        // RelativeLayout viewLeft = (RelativeLayout) layout.findViewById(R.id.relayPost);
        MentalHelpModel fixture = list.get(position);
        tvDesc = (TextView) layout.findViewById(R.id.tv_desc);
        tvTitle = (TextView) layout.findViewById(R.id.tv_title);
        RelativeLayout backGround = (RelativeLayout) layout.findViewById(R.id.rlBackground);
        ImageView imgBackground = (ImageView) layout.findViewById(R.id.imgBackground);

        backGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFixtureItemClick.onClickFixture(position);
            }
        });

        imgBackground.setImageResource(lstBg.get(position));
        addFixture(fixture);
        collection.addView(layout);
        return layout;
    }

    private void addFixture(MentalHelpModel mentalHelpModel) {
        tvDesc.setText(mentalHelpModel.getDesc());
        tvTitle.setText(mentalHelpModel.getDetail());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }
    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
