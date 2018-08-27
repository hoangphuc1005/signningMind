package com.MentalHealth.mental.diary.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.dbdiary.DBHistory;
import com.MentalHealth.mental.diary.model.DiaryModel;

import java.util.List;

public class DiaryNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DiaryModel> items;
    private OnClickRecycleView clickRecycleView;
    private DiaryModel diaryModel;

    public DiaryNoteAdapter(Context mContext, List<DiaryModel> items, OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_list_diary_favorite, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        diaryModel = items.get(position);
        itemViewHolder.tvTitleDiary.setText(diaryModel.getTitleOfDiary());
        itemViewHolder.tvDateDiary.setText(diaryModel.getDateOfDiary());
        itemViewHolder.tvMonthDiary.setText(diaryModel.getMonthOfDiary());
        itemViewHolder.imgDeleteDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DBHistory dbHistory = new DBHistory(context);
                dbHistory.deleteDiary(diaryModel);
                items.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateDiary;
        TextView tvMonthDiary;
        TextView tvTitleDiary;
        ImageView imgDeleteDiary;


        public ItemViewHolder(final View itemView) {
            super(itemView);
            tvMonthDiary = (TextView) itemView.findViewById(R.id.tvMonthDiaryFavorite);
            tvDateDiary = (TextView) itemView.findViewById(R.id.tvDateDiaryFavorite);
            tvTitleDiary = (TextView) itemView.findViewById(R.id.tvTitleDiary);
            imgDeleteDiary = (ImageView) itemView.findViewById(R.id.imgDeleteDiary);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecycleView.setOnItemClick(diaryModel);
                }
            });

        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(DiaryModel diaryModel);
    }
}
