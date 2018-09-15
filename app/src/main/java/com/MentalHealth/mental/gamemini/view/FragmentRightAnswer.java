package com.MentalHealth.mental.gamemini.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;

public class FragmentRightAnswer extends BaseFragment {
    private ImageView btnBackAnswer;
    private Bundle bundle;
    private TextView tvContent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_right_answer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = getArguments();
        tvContent = (TextView) findViewById(R.id.textContentRightAnswer);
        SpannableString content = new SpannableString(Html.fromHtml(bundle.getString("ABOUT_ANSWER")));
        tvContent.setText(content);
        btnBackAnswer = (ImageView) findViewById(R.id.imBackAnswerContent);
        btnBackAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                onMoveParentFragments(new MiniGameAnswerFragment(), bundle);
            }
        });
    }
}
