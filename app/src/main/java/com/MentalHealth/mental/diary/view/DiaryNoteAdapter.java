package com.MentalHealth.mental.diary.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.dbdiary.DBHistory;
import com.MentalHealth.mental.diary.model.DiaryModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiaryNoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DiaryModel> items;
    private OnClickRecycleView clickRecycleView;
    private DiaryModel diaryModel;
    private Integer type = 0;

    public DiaryNoteAdapter(Context mContext, List<DiaryModel> items, OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }

    public void updateDay(List<DiaryModel> itemsList, Integer typeNote) {
        type = typeNote;
        items = itemsList;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_list_diary_favorite, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        diaryModel = items.get(position);

        if (type == 2) {
            Collections.sort(items, new Comparator<DiaryModel>(){
                @Override
                public int compare(DiaryModel diaryModel, DiaryModel t1) {
                    if(diaryModel.getYearOfDiary().equalsIgnoreCase(t1.getYearOfDiary())){
                        return 0;
                    }
                    return 1;

                }

            });
            itemViewHolder.rlNote.setVisibility(View.VISIBLE);
            itemViewHolder.tvDateDiary.setVisibility(View.GONE);
            itemViewHolder.tvMonthDiary.setVisibility(View.GONE);
            itemViewHolder.imgDeleteDiary.setVisibility(View.GONE);
            itemViewHolder.viewLineNote.setVisibility(View.GONE);
            itemViewHolder.tvTitleDiary.setText(diaryModel.getYearOfDiary());
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecycleView.setOnItemClick(diaryModel, 1);
                }
            });
        } else if (type == 3) {
            itemViewHolder.rlNote.setVisibility(View.VISIBLE);
            itemViewHolder.tvTitleDiary.setText(diaryModel.getMonthOfYear());
            itemViewHolder.tvDateDiary.setVisibility(View.GONE);
            itemViewHolder.tvMonthDiary.setVisibility(View.GONE);
            itemViewHolder.imgDeleteDiary.setVisibility(View.GONE);
            itemViewHolder.viewLineNote.setVisibility(View.GONE);
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecycleView.setOnItemClick(diaryModel, 2);
                }
            });
        } else {
            itemViewHolder.rlNote.setVisibility(View.VISIBLE);
            itemViewHolder.tvTitleDiary.setText(diaryModel.getTitleOfDiary());
            itemViewHolder.tvDateDiary.setText(diaryModel.getDateOfDiary());
            itemViewHolder.tvMonthDiary.setText(diaryModel.getMonthOfDiary());
            itemViewHolder.tvDateDiary.setVisibility(View.VISIBLE);
            itemViewHolder.tvMonthDiary.setVisibility(View.VISIBLE);
            itemViewHolder.imgDeleteDiary.setVisibility(View.VISIBLE);
            itemViewHolder.viewLineNote.setVisibility(View.VISIBLE);
            itemViewHolder.imgDeleteDiary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DBHistory dbHistory = new DBHistory(context);
                    dbHistory.deleteDiary(diaryModel);
                    items.remove(position);
                    notifyDataSetChanged();
                }
            });
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecycleView.setOnItemClick(diaryModel, 0);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateDiary;
        TextView tvMonthDiary;
        View viewLineNote;
        TextView tvTitleDiary;
        ImageView imgDeleteDiary;
        RelativeLayout rlNote;


        public ItemViewHolder(final View itemView) {
            super(itemView);
            tvMonthDiary = (TextView) itemView.findViewById(R.id.tvMonthDiaryFavorite);
            tvDateDiary = (TextView) itemView.findViewById(R.id.tvDateDiaryFavorite);
            tvTitleDiary = (TextView) itemView.findViewById(R.id.tvTitleDiary);
            imgDeleteDiary = (ImageView) itemView.findViewById(R.id.imgDeleteDiary);
            rlNote = (RelativeLayout) itemView.findViewById(R.id.rlNote);
            viewLineNote = itemView.findViewById(R.id.viewLineNote);


        }
    }


    public interface OnClickRecycleView {
        void setOnItemClick(DiaryModel diaryModel, Integer type);
    }
}
