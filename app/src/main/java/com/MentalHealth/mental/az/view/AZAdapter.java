package com.MentalHealth.mental.az.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.az.model.DataAZModel;

import java.util.List;

public class AZAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DataAZModel> items;
    private AZAdapter.OnClickRecycleView clickRecycleView;

    public AZAdapter(Context mContext, List<DataAZModel> items, AZAdapter.OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_view_fragment_month_info, parent, false);
        return new AZAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AZAdapter.ItemViewHolder itemViewHolder = (AZAdapter.ItemViewHolder) holder;
        final DataAZModel infoNewModel = items.get(position);
        if (infoNewModel.getId() == 1) {
            itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_roiloanloau);
        } else if (infoNewModel.getId() == 2) {
            itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_roiloantramcam);
        } else if (infoNewModel.getId() == 3) {
            itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_roiloanlungcuc);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecycleView.setOnItemClick(infoNewModel.getId());
            }
        });
    }

    public void updateAnswers(List<DataAZModel> itemsList) {
        items = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout imgIcons;
        TextView tvDateOfMonth;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (RelativeLayout) itemView.findViewById(R.id.rlMonthInfo);
            tvDateOfMonth = (TextView) itemView.findViewById(R.id.tvDateOfMonth);
            tvDateOfMonth.setVisibility(View.GONE);

        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(int position);
    }
}
