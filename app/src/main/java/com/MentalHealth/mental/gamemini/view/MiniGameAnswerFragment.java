package com.MentalHealth.mental.gamemini.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.gamemini.model.AnswerModel;
import com.MentalHealth.mental.gamemini.model.QuizDataDetail;
import com.MentalHealth.mental.gamemini.model.QuizModelDetail;
import com.MentalHealth.mental.serverapi.ApiUtils;
import com.MentalHealth.mental.serverapi.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiniGameAnswerFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<AnswerModel> listInfoNew;
    private ImageView btnBackAnswer, btnNextAnswer;
    private SOService mService;
    private TextView tvTitleAnswer, tvLevel;
    private RecyclerView recyclerInfo;
    private String aboutAnswer;
    int question = 0;
    int size = 0;
    private int id;
    Bundle bundle;
    public static final String MY_PREFERENCE = "Account";
    public static final String CURRENT_COUNT = "CURRENT_COUNT";
    SharedPreferences sharedpreferences;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mini_game;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        updateBackActionbar();
//        callSOS();
//        handleBackPressMain();
    }

    private void init() {
        recyclerInfo = (RecyclerView) findViewById(R.id.recyclerAnswer);
        listInfoNew = new ArrayList<>();
        tvTitleAnswer = (TextView) findViewById(R.id.tvTitleAnswer);
        tvLevel = (TextView) findViewById(R.id.tvLevel);
        mService = ApiUtils.getSOService();
        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        bundle = getArguments();
        String idQuiz = bundle.getString("id");
        if (idQuiz != null) {
            id = Integer.parseInt(idQuiz);
        } else if (bundle.getInt("quiz_cho") > 0) {
            id = bundle.getInt("quiz_cho");
        } else {
            id = sharedpreferences.getInt("ID", 0);
        }
        btnBackAnswer = (ImageView) findViewById(R.id.btnBackAnswer);
        btnNextAnswer = (ImageView) findViewById(R.id.btnNextAnswer);
        btnNextAnswer.setOnClickListener(this);
        btnBackAnswer.setOnClickListener(this);
        addDataInfo();
    }

    private void addDataInfo() {
        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                Context.MODE_PRIVATE);
        question = sharedpreferences.getInt(CURRENT_COUNT, 0);
        mService.getQuizInfo(String.valueOf(id)).enqueue(new Callback<QuizModelDetail>() {
            @Override
            public void onResponse(Call<QuizModelDetail> call, Response<QuizModelDetail> response) {
                if (response != null) {
                    size = response.body().getData().size();
                    if (question < size) {
                        tvTitleAnswer.setText(response.body().getData().get(question).getContent());
                        tvLevel.setText("LEVEL" + " " + response.body().getData().get(question).getLevelId());
                        aboutAnswer = response.body().getData().get(question).getAboutAnswer();
                        listInfoNew.add(new AnswerModel(response.body().getData().get(question).getChoose1(),
                                R.drawable.img_a, response.body().getData().get(question).getAnswer()));
                        listInfoNew.add(new AnswerModel(response.body().getData().get(question).getChoose2(),
                                R.drawable.img_b, response.body().getData().get(question).getAnswer()));
                        listInfoNew.add(new AnswerModel(response.body().getData().get(question).getChoose3(),
                                R.drawable.img_c, response.body().getData().get(question).getAnswer()));
                        listInfoNew.add(new AnswerModel(response.body().getData().get(question).getChoose4(),
                                R.drawable.img_d, response.body().getData().get(question).getAnswer()));

                        MiniGameAnswerAdapter infoAdapter = new MiniGameAnswerAdapter(getContext(), listInfoNew);
                        infoAdapter.notifyDataSetChanged();
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                        recyclerInfo.setLayoutManager(mLayoutManager);
                        recyclerInfo.setAdapter(infoAdapter);
                    } else {
                        sharedpreferences = getContext().getSharedPreferences(MY_PREFERENCE,
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean("LEVEL", true);
                        editor.apply();
                        onMoveParentFragments(new GameMiniFragment(), new Bundle());
                        editor.remove("ID");
                        editor.remove(CURRENT_COUNT);
                    }

                }
            }

            @Override
            public void onFailure(Call<QuizModelDetail> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBackAnswer:
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
                break;
            case R.id.btnNextAnswer:
                if (question < size) {
                    if (sharedpreferences.getBoolean("true", false) == true) {
                        question = question + 1;
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(CURRENT_COUNT, question);
                        editor.putInt("ID", id);
                        editor.apply();
                        Bundle bundle = new Bundle();
                        bundle.putString("ABOUT_ANSWER", aboutAnswer);
                        onMoveParentFragments(new FragmentRightAnswer(), bundle);
                    }

                } else {
                    onMoveParentFragments(new GameMiniFragment(), new Bundle());
                }


                break;
        }
    }
}
