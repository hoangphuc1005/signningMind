package com.MentalHealth.mental.monthinfo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.infonew.model.InfoNewModel;
import com.MentalHealth.mental.library.model.DataDoccumentModel;
import com.MentalHealth.mental.monthinfo.model.DataAlldayModel;

import java.util.List;

public class MonthInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DataAlldayModel> items;
    private OnClickRecycleView clickRecycleView;

    public MonthInfoAdapter(Context mContext, List<DataAlldayModel> items, OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_view_fragment_month_info, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final DataAlldayModel infoNewModel = items.get(position);
        if (infoNewModel.getActive().equals("0")) {
            itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_ngaybikhoa);
            holder.itemView.setEnabled(false);
        } else if (infoNewModel.getActive().equals("1")) {
            if (infoNewModel.getWasRead() == false) {
                itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_ngaychuaxem);
                holder.itemView.setEnabled(true);
            } else if (infoNewModel.getWasRead() == true) {
                itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_ngaydaxem);
                holder.itemView.setEnabled(true);
            }

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecycleView.setOnItemClick(infoNewModel.getId());
            }
        });

        itemViewHolder.tvDateOfMonth.setText(infoNewModel.getName());
    }

    public void updateAnswers(List<DataAlldayModel> itemsList) {
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

        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(int position);
    }

}
