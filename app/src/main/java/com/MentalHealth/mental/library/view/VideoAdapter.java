package com.MentalHealth.mental.library.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.library.model.DataDoccumentModel;
import com.MentalHealth.mental.library.model.DataVideoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DataVideoModel> items;
    private VideoAdapter.OnClickRecycleView clickRecycleView;

    public VideoAdapter(Context mContext, List<DataVideoModel> items, VideoAdapter.OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_view_fragment_infonew, parent, false);
        return new VideoAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoAdapter.ItemViewHolder itemViewHolder = (VideoAdapter.ItemViewHolder) holder;
        final DataVideoModel infoNewModel = items.get(position);
        itemViewHolder.tvTitle.setText(infoNewModel.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecycleView.setOnItemClick(infoNewModel.getUrl());
            }
        });

    }

    public void updateAnswers(List<DataVideoModel> itemsList) {
        items = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcons;
        TextView tvTitle;
        RelativeLayout bgMain;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (ImageView) itemView.findViewById(R.id.imgInfo);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleInfo);
            bgMain = (RelativeLayout) itemView.findViewById(R.id.bg_info_new);

        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(String url);
    }
}
