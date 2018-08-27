package com.MentalHealth.mental.infonew.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.infonew.model.InfoNewModel;

import java.util.List;

public class InfoNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<InfoNewModel> items;
    private OnClickRecycleView clickRecycleView;

    public InfoNewAdapter(Context mContext, List<InfoNewModel> items, OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_view_fragment_infonew, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final InfoNewModel infoNewModel = items.get(position);
        itemViewHolder.imgIcons.setImageResource(infoNewModel.getImgInfo());
        itemViewHolder.tvTitle.setText(infoNewModel.getTitleInfo());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcons;
        TextView tvTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (ImageView) itemView.findViewById(R.id.imgInfo);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleInfo);

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
