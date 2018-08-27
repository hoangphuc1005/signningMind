package com.MentalHealth.mental.home.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.az.view.AZFragment;
import com.MentalHealth.mental.base.BaseFragment;
import com.MentalHealth.mental.diary.view.DiaryFragment;
import com.MentalHealth.mental.gamemini.view.GameMiniFragment;
import com.MentalHealth.mental.home.model.MainObject;
import com.MentalHealth.mental.home.model.MentalHelpModel;
import com.MentalHealth.mental.base.customview.CircleIndicator;
import com.MentalHealth.mental.base.customview.WrapContentViewPager;
import com.MentalHealth.mental.home.view.MainAdapter;
import com.MentalHealth.mental.home.view.SlidingAdapter;
import com.MentalHealth.mental.infonew.view.InfoNewDetailFragment;
import com.MentalHealth.mental.infonew.view.InfoNewFragment;
import com.MentalHealth.mental.library.view.LibraryFragment;
import com.MentalHealth.mental.monthinfo.view.MonthInfoFragment;

import java.util.ArrayList;
import java.util.Random;


public class FragmentHome extends BaseFragment implements View.OnClickListener {
    private RecyclerView lvNewsHot;
    private WrapContentViewPager viewPager;
    private CircleIndicator pageIndicator;
    private ArrayList<MentalHelpModel> arrayList = new ArrayList<>();
    private ArrayList<MainObject> listDrugs = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private SlidingAdapter pagerAdapter;
    private ArrayList<Integer> arrBackGround = new ArrayList<>();
    private ArrayList<Integer> lstImage = new ArrayList<>();

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        handleBackPress();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.txt_home);
        setUserVisibleHint(true);
        updateHomeActionbar();

        addBackground();
        init();
        getDataSliding();
        getDataDrugs();
        callSOS();
        setTitleActionBar("Trang chủ");
    }

    private void init() {
//        scrollingView = (ScrollView) findViewById(R.id.scrollView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setEnabled(false);
        viewPager = (WrapContentViewPager) findViewById(R.id.view_pager);
        pageIndicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        lvNewsHot = (RecyclerView) findViewById(R.id.list_news_hot);
        lvNewsHot.setLayoutManager(new GridLayoutManager(getActivity(), 2));

    }

    private void addBackground() {
        arrBackGround.add(R.drawable.ic_tram_cam);
        arrBackGround.add(R.drawable.ic_tram_cam);
        arrBackGround.add(R.drawable.ic_tram_cam);
        arrBackGround.add(R.drawable.ic_tram_cam);
        arrBackGround.add(R.drawable.ic_tram_cam);
        arrBackGround.add(R.drawable.ic_tram_cam);
        arrBackGround.add(R.drawable.ic_tram_cam);
        arrBackGround.add(R.drawable.ic_tram_cam);

        randomBackGround();
    }

    private ArrayList<Integer> randomBackGround() {
        lstImage = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
//            lstImage.add(arrBackGround[i]);
            int k = new Random().nextInt(arrBackGround.size());
            lstImage.add(arrBackGround.get(k));
            arrBackGround.remove(k);
        }
        return lstImage;
    }

    private void getDataSliding() {
        arrayList.add(new MentalHelpModel("", ""));
        arrayList.add(new MentalHelpModel("", ""));
        arrayList.add(new MentalHelpModel("", ""));
        arrayList.add(new MentalHelpModel("", ""));
        arrayList.add(new MentalHelpModel("", ""));
        arrayList.add(new MentalHelpModel("", ""));
        arrayList.add(new MentalHelpModel("", ""));
        arrayList.add(new MentalHelpModel("", ""));
        pagerAdapter = new SlidingAdapter(arrayList, getActivity(), lstImage, clickFixtureItem);
        viewPager.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(viewPager);
    }

    SlidingAdapter.FixtureItemClick clickFixtureItem = new SlidingAdapter.FixtureItemClick() {
        @Override
        public void onClickFixture(int position) {
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            fragment = new InfoNewDetailFragment();
            onMoveParentFragments(fragment, bundle);
        }
    };

    private void getDataDrugs() {
        listDrugs.add(new MainObject(R.drawable.img_tintuc_1080));
        listDrugs.add(new MainObject(R.drawable.img_month_info));
        listDrugs.add(new MainObject(R.drawable.img_a_z));
        listDrugs.add(new MainObject(R.drawable.img_thuvien));
        listDrugs.add(new MainObject(R.drawable.img_diary_out));
        listDrugs.add(new MainObject(R.drawable.img_game_mini));
        MainAdapter drugsAdapter = new MainAdapter(getActivity(), listDrugs, clickItems);
        lvNewsHot.setAdapter(drugsAdapter);
    }

    MainAdapter.OnClickRecycleView clickItems = new MainAdapter.OnClickRecycleView() {
        @Override
        public void setOnItemClick(int position) {
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    fragment = new InfoNewFragment();
                    onMoveParentFragments(fragment, bundle);
                    break;
                case 1:
                    fragment = new MonthInfoFragment();
                    onMoveParentFragments(fragment, bundle);
                    break;
                case 2:
                    fragment = new AZFragment();
                    onMoveParentFragments(fragment, bundle);
                    break;
                case 3:
                    fragment = new LibraryFragment();
                    onMoveParentFragments(fragment, bundle);
                    break;
                case 4:
                    fragment = new DiaryFragment();
                    onMoveParentFragments(fragment, bundle);
                    break;
                case 5:
                    fragment = new GameMiniFragment();
                    onMoveParentFragments(fragment, bundle);
                    break;
            }
        }

    };
    public void handleBackPress(boolean isHandle) {
        if (isHandle) {
            getView().setFocusableInTouchMode(true);
            if (!getView().isFocused())
                getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        // handle back button
                            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle(R.string.txt_finish);
                            builder.setMessage(R.string.txt_notify_finish);
                            builder.setPositiveButton(R.string.txt_ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().finish();
                                }
                            });
                            builder.setNegativeButton(R.string.txt_cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        return true;
                    }
                    return false;
                }
            });
        } else {
            getView().setFocusableInTouchMode(false);
            if (getView().isFocused())
                getView().clearFocus();
            getView().setOnKeyListener(null);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        handleBackPress(true);

    }
}
