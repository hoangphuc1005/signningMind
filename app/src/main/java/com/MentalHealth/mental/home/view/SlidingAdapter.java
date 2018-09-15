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
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.home.model.MentalHelpModel;
import com.MentalHealth.mental.infonew.model.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SlidingAdapter extends PagerAdapter {
    private TextView tvTitle;
    private TextView tvDesc;
    private Context context;
    private List<Data> list;
    private LayoutInflater mInflator;
    private ArrayList<Integer> lstBg;
    private FixtureItemClick mFixtureItemClick;

    public SlidingAdapter(List<Data> list, Context context, ArrayList<Integer> listBg, FixtureItemClick fixtureItemClick) {
        this.list = list;
        this.context = context;
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
        final Data fixture = list.get(position);
        tvDesc = (TextView) layout.findViewById(R.id.tv_desc);
        tvTitle = (TextView) layout.findViewById(R.id.tv_title);
        RelativeLayout backGround = (RelativeLayout) layout.findViewById(R.id.rlBackground);
        ImageView imgBackground = (ImageView) layout.findViewById(R.id.imgBackground);

        backGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFixtureItemClick.onClickFixture(fixture.getId());
            }
        });
        Picasso.with(context)
                .load(Constant.URL_IMAGE + fixture.getImage())
                .placeholder(R.drawable.ic_tram_cam)
                .fit().centerCrop()
                .into(imgBackground);
        addFixture(fixture);
        collection.addView(layout);
        return layout;
    }

    public void updateAnswers(List<Data> itemsList) {
        list = itemsList;
        notifyDataSetChanged();
    }

    private void addFixture(Data mentalHelpModel) {
        tvDesc.setText(mentalHelpModel.getTitle());
        tvTitle.setText(mentalHelpModel.getTitle());
    }

    @Override
    public int getCount() {
        return 3;
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
