package com.MentalHealth.mental.servicefcm.notifi.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.servicefcm.notifi.model.NotificationModel;

import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NotificationModel> items;
    private NotificationListAdapter.OnClickRecycleView clickRecycleView;

    public NotificationListAdapter(Context mContext, List<NotificationModel> items, NotificationListAdapter.OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_list_nitification, parent, false);
        return new NotificationListAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotificationListAdapter.ItemViewHolder itemViewHolder = (NotificationListAdapter.ItemViewHolder) holder;
        final NotificationModel notificationModel = items.get(position);
        itemViewHolder.tvTitle.setText(notificationModel.getTitle());
        itemViewHolder.tvMessage.setText(notificationModel.getMessage());


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        TextView tvTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleNotification);
            tvMessage = (TextView) itemView.findViewById(R.id.tvMessageNotification);

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
