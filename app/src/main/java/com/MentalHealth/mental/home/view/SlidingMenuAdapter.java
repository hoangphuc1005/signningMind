package com.MentalHealth.mental.home.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.home.model.SlidingMenuModel;

import java.util.List;


public class SlidingMenuAdapter extends BaseAdapter {
    private Context context;
    private List<SlidingMenuModel> items;
    private String status = "Trang chủ";

    public SlidingMenuAdapter(Context context, List<SlidingMenuModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setPosition(String mStatus) {
        this.status = mStatus;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        SlidingMenuModel slidingMenuModel = (SlidingMenuModel) getItem(position);
        if (convertView == null) {
            viewHolder = new SlidingMenuAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_lv_sliding_menu, null);
            viewHolder.tvItems = (TextView) convertView.findViewById(R.id.tvItemsMenu);
            viewHolder.imgIcons = (ImageView) convertView.findViewById(R.id.imgIconsMenu);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SlidingMenuAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.imgIcons.setImageResource(slidingMenuModel.getImgIcons());
        viewHolder.tvItems.setText(slidingMenuModel.getTvItems());
        if(status.equalsIgnoreCase(slidingMenuModel.getTvItems())){
            viewHolder.tvItems.setTypeface(null, Typeface.BOLD);

        }else{
            viewHolder.tvItems.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tvItems;
        ImageView imgIcons;

    }
}

