package com.MentalHealth.mental.library.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.Constant;
import com.MentalHealth.mental.library.model.DataDoccumentModel;
import com.MentalHealth.mental.library.model.DoccumentDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DoccumentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DataDoccumentModel> items;
    private DoccumentAdapter.OnClickRecycleView clickRecycleView;

    public DoccumentAdapter(Context mContext, List<DataDoccumentModel> items, DoccumentAdapter.OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_view_fragment_infonew, parent, false);
        return new DoccumentAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DoccumentAdapter.ItemViewHolder itemViewHolder = (DoccumentAdapter.ItemViewHolder) holder;
        final DataDoccumentModel infoNewModel = items.get(position);

        int i = infoNewModel.getId();
        switch (i) {
            case 5:
                itemViewHolder.imgIcons.setImageResource(R.drawable.img_sinhvien);
                break;
            case 6:
                itemViewHolder.imgIcons.setImageResource(R.drawable.img_loaulantoa);
                break;
            case 7:
                itemViewHolder.imgIcons.setImageResource(R.drawable.img_doipho);
                break;

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecycleView.setOnItemClick(infoNewModel.getId());
            }
        });
    }

    public void updateAnswers(List<DataDoccumentModel> itemsList) {
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

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (ImageView) itemView.findViewById(R.id.imgInfo);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleInfo);
            tvTitle.setVisibility(View.GONE);


        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(int position);
    }
}
