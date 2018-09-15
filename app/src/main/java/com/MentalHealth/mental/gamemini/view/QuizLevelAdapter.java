package com.MentalHealth.mental.gamemini.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.gamemini.model.LevelDataQuiz;

import java.util.List;

public class QuizLevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<LevelDataQuiz> items;
    private QuizLevelAdapter.OnClickRecycleView clickRecycleView;
    SharedPreferences sharedpreferences;
    public static final String MY_PREFERENCE = "Account";

    public QuizLevelAdapter(Context mContext, List<LevelDataQuiz> items, QuizLevelAdapter.OnClickRecycleView onClickRecycleView) {
        this.context = mContext;
        this.items = items;
        this.clickRecycleView = onClickRecycleView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_view_fragment_month_info, parent, false);
        return new QuizLevelAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        QuizLevelAdapter.ItemViewHolder itemViewHolder = (QuizLevelAdapter.ItemViewHolder) holder;
        final LevelDataQuiz infoNewModel = items.get(position);
        sharedpreferences = context.getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        Boolean check = sharedpreferences.getBoolean("LEVEL", false);
        if (position == 0) {
            itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_framelevel);
        } else {
            itemViewHolder.imgIcons.setEnabled(false);

            if (check == false) {
                itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_framelevellock);
            } else {
                itemViewHolder.imgIcons.setBackgroundResource(R.drawable.img_framelevel);
            }
        }

        itemViewHolder.tvDateOfMonth.setText(infoNewModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecycleView.setOnItemClick(infoNewModel.getId());
            }
        });
    }

    public void updateAnswers(List<LevelDataQuiz> itemsList) {
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
