package com.MentalHealth.mental.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.home.model.MainObject;

import java.util.List;



public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MainObject> items;
    private OnClickRecycleView clickRecycleView;

    public MainAdapter(Context mContext, List<MainObject> items, OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_recycle_fragment_main, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final MainObject mainObject = items.get(position);
        itemViewHolder.imgIcons.setImageResource(mainObject.getImgDrugs());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcons;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (ImageView) itemView.findViewById(R.id.img_drugs);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecycleView.setOnItemClick(getLayoutPosition());
                }
            });
        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(int position);
    }

}