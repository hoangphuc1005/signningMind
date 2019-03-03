package com.MentalHealth.mental.infonew.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.home.model.Item;
import com.MentalHealth.mental.infonew.model.Data;
import com.MentalHealth.mental.infonew.model.InfoNew;
import com.MentalHealth.mental.infonew.model.InfoNewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InfoNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    private List<Data> items;
    private List<Data> mFilteredList;
    private OnClickRecycleView clickRecycleView;

    public InfoNewAdapter(Context mContext, List<Data> items, OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        mFilteredList = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_view_fragment_infonew, parent, false);
        return new ItemViewHolder(itemView);
    }

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final Data infoNewModel = mFilteredList.get(position);
        itemViewHolder.tvTitle.setText(infoNewModel.getTitle());
        // itemViewHolder.bgMain.setBackgroundResource(R.drawable.bg_content_diary);
        itemViewHolder.imgIcons.setVisibility(View.GONE);
        itemViewHolder.webView1.setVisibility(View.VISIBLE);
        itemViewHolder.webView1.getSettings().setJavaScriptEnabled(true);
        itemViewHolder.webView1.loadUrl(Constant.URL_IMAGE + infoNewModel.getImage());
        itemViewHolder.webView1.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        itemViewHolder.webView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    return false;
                }

                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                    clickRecycleView.setOnItemClick(infoNewModel);

                }
                return false;
            }
        });
        itemViewHolder.bgMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecycleView.setOnItemClick(infoNewModel);
            }
        });

    }

    public void updateAnswers(List<Data> itemsList) {
        items = itemsList;
        mFilteredList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcons;
        TextView tvTitle;
        RelativeLayout bgMain;
        WebView webView1;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (ImageView) itemView.findViewById(R.id.imgInfo);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleInfo);
            bgMain = (RelativeLayout) itemView.findViewById(R.id.bg_info_new);
            webView1 = (WebView) itemView.findViewById(R.id.webView1);

        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(Data position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = items;
                } else {

                    ArrayList<Data> filteredList = new ArrayList<>();

                    for (Data androidVersion : items) {

                        if (androidVersion.getTitle().trim().toUpperCase(Locale.ENGLISH).contains(charString.toUpperCase())) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
