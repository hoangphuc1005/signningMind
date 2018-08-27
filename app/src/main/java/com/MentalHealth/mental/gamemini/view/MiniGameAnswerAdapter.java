package com.MentalHealth.mental.gamemini.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.gamemini.model.AnswerModel;
import com.MentalHealth.mental.infonew.model.InfoNewModel;

import java.util.List;

public class MiniGameAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<AnswerModel> items;


    public MiniGameAnswerAdapter(Context mContext, List<AnswerModel> items) {
        this.context = mContext;
        this.items = items;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.custom_answer_quiz, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final AnswerModel infoNewModel = items.get(position);
        itemViewHolder.relativeLayout.setBackgroundResource(infoNewModel.getBgQuestion());
        itemViewHolder.tvTitle.setText(infoNewModel.getQuestion());
        itemViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (items.get(position).getQuestion() == "Việt Nam") {
                    items.get(position).setChoiceAnswer(View.VISIBLE);
                    itemViewHolder.imgIcons.setVisibility(items.get(position).getChoiceAnswer());
                    itemViewHolder.imgIcons.setImageResource(
                            R.drawable.ic_true);

                } else {
                    items.get(position).setChoiceAnswer(View.VISIBLE);
                    itemViewHolder.imgIcons.setVisibility(items.get(position).getChoiceAnswer());
                    itemViewHolder.imgIcons.setImageResource(R.drawable.ic_false);

                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcons;
        TextView tvTitle;
        RelativeLayout relativeLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgIcons = (ImageView) itemView.findViewById(R.id.imgChoice);
            tvTitle = (TextView) itemView.findViewById(R.id.tvAnswerQuiz);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.bg_choice_answer);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


}
