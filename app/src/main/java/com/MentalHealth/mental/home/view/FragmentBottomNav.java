package com.MentalHealth.mental.home.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;

import com.MentalHealth.mental.MainActivity;
import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class FragmentBottomNav extends BaseFragment {
    private ActionBar mActionBar;
    private BottomNavigationView bottomNavigationView;
    private BottomBar bottomBar;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_bottom_navv;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        // updateActionBar();
//        mUserModel = SharedPreference.getAccountHasLogin(getActivity());
        init();

    }

    private void init() {
        bottomBar = (BottomBar) findViewById(R.id.navigation);
//        if(mUserModel.getIdSocial().isEmpty()) {
//            bottomBar.setItems(R.xml.bottombar_tabs_no_user);
//        }else{
//            bottomBar.setItems(R.xml.bottombar_tabs);
//        }
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment selectedFragment = null;
                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constants.KEY_USER, mUserModel);
                switch (tabId) {
                    case R.id.tab_home:
                        selectedFragment = new FragmentHome();
                        break;
                    case R.id.tab_fan_page:
//                        selectedFragment = new FragmentFanPage(backPressListener);
                        break;
                    case R.id.tab_video:
//                        selectedFragment = new FragmentSetting(videoFullScreenLinstener, backPressListener);
//                        selectedFragment = new FragmentPlayStream(videoFullScreenLinstener, backPressListener);
//                        selectedFragment = new FragmentVideoList(openSearchBoxListener);
                        break;
//                    case R.id.tab_user:
//                        selectedFragment = new FragmentUser();
//                        break;
                }
                if (selectedFragment != null) {
                    onMoveParentFragmentNotBack(selectedFragment, bundle);

                }
            }

        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Fragment selectedFragment = null;
                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constants.KEY_USER, mUserModel);
                switch (tabId) {
                    case R.id.tab_home:
//                        selectedFragment = new FragmentHome();
                        break;
                    case R.id.tab_video:
//                        selectedFragment = new FragmentSetting(videoFullScreenLinsteBasener, backPressListener);
//                        selectedFragment = new FragmentPlayStream(videoFullScreenLinstener, backPressListener);
//                        selectedFragment = new FragmentVideoList(openSearchBoxListener);
                        break;
//                    case R.id.tab_user:
//                        selectedFragment = new FragmentUser();
//                        break;
                }
            }
        });
    }

    public void onMoveParentFragmentNotBack(Fragment fragment, final Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
        //   transaction.addToBackStack(fragment_home);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    private void updateActionBar() {
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setTitle(R.string.txt_news_top);
        }
        updateHomeActionbar();
    }


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
                        if (bottomBar != null && bottomBar.getCurrentTabPosition() != 0) {
                            bottomBar.selectTabWithId(R.id.tab_home);
                        } else {
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

                        }
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
