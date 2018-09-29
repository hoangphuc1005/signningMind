package com.MentalHealth.mental.gamemini.view;

import android.content.Context;
import android.content.SharedPreferences;
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
    public static final String MY_PREFERENCE = "Account";
    public static final String CURRENT_COUNT = "CURRENT_COUNT";
    SharedPreferences sharedpreferences;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_right_answer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("true");
        editor.apply();
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
