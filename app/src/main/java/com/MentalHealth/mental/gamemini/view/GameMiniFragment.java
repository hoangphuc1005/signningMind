package com.MentalHealth.mental.gamemini.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;


public class GameMiniFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout rlLevelFirst;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_quiz;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUserVisibleHint(true);
        setTitleActionBar("Mini Game");
        updateHomeActionbar();
        updateBackActionbarCustomBack();
        comeBackHomeScreen();
        handleBackPress();
        rlLevelFirst = (RelativeLayout) findViewById(R.id.rlLevelFirst);
        rlLevelFirst.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlLevelFirst:
                onMoveParentFragments(new MiniGameAnswerFragment(), new Bundle());
                break;
        }
    }


}

