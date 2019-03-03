package com.MentalHealth.mental.az.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.az.model.DataAZModel;
import com.MentalHealth.mental.base.Constant;
import com.squareup.picasso.Picasso;

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
                (R.layout.custom_az_fragment, parent, false);
        return new AZAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AZAdapter.ItemViewHolder itemViewHolder = (AZAdapter.ItemViewHolder) holder;
        final DataAZModel infoNewModel = items.get(position);
        itemViewHolder.tvDateOfMonth.setText(infoNewModel.getName());
        Picasso.with(context)
                .load(Constant.URL_IMAGE + infoNewModel.getImage())
                .fit().centerCrop()
                .into(itemViewHolder.imgIcons);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecycleView.setOnItemClick(infoNewModel.getId());
            }
        });
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
        ImageView imgIcons;
        TextView tvDateOfMonth;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (ImageView) itemView.findViewById(R.id.imgAZ);
            tvDateOfMonth = (TextView) itemView.findViewById(R.id.tvTitleAZ);

        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(int position);
    }
}
