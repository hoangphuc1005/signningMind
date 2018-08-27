package com.MentalHealth.mental.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.home.view.FragmentBottomNav;
import com.MentalHealth.mental.home.view.FragmentHome;
import com.MentalHealth.mental.sos.view.SosFragment;


public abstract class BaseFragment extends Fragment implements ViewTreeObserver.OnGlobalLayoutListener {


    private DrawerLayout mDrawerLayout;

    protected View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null)
                parent.removeView(mRootView);
        }
        try {
            mRootView = inflater.inflate(layoutId, container, false);
            mRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        return mRootView;
    }

    public void switchChildFragmentWithBundle(String name, boolean addToBackStack, Bundle mBundle) {
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayouts);
        Fragment fragment = Fragment.instantiate(getActivity(), name, mBundle);
        if (addToBackStack) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_view, fragment)
                    .addToBackStack(name).commit();
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_view, fragment)
                    .commit();
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    public void onMoveParentFragments(Fragment fragment, final Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_view, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onMoveChildrenFragments(Fragment fragment, final Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_view, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onMoveFragmentNotTag(Fragment fragment, final Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_view, fragment);
        transaction.addToBackStack("Tag");
        transaction.commit();
    }


    public void onMoveFragmentMain(Fragment fragment, final Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_view, fragment);
        transaction.commit();
    }

    public void reloadFragment() {
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.fragment);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(currentFragment);
        fragmentTransaction.attach(currentFragment);
        fragmentTransaction.commit();
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

    public View findViewById(int resId) {
        return mRootView.findViewById(resId);
    }

    public void finishFragment(Activity activity, String name) {
        if (activity != null && name != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    @Override
    public void onGlobalLayout() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        } else {
            mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

    }

    public abstract int getLayoutId();

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void handleBackPress() {
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayouts);
        View view = getView();
        if (view == null)
            return;
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    } else {
                        int canback = getFragmentManager().getBackStackEntryCount();
                        if (canback > 0 && canback < 2) {
                            FragmentManager manager = getFragmentManager();
                            manager.popBackStack();
                            if (toolbar != null) {
                                toolbar.setTitle(R.string.txt_home);
                                Fragment fragment = new FragmentHome();
                                onMoveFragmentMain(fragment, getArguments());
                            }
                        } else if (canback > 1) {
                            FragmentManager manager = getFragmentManager();
                            manager.popBackStack("Tag", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            if (toolbar != null) {
                                toolbar.setTitle(R.string.txt_home);
                                Fragment fragment = new FragmentHome();
                                onMoveFragmentMain(fragment, getArguments());
                            }
                        } else {
                            Fragment fragment = new FragmentHome();
                            onMoveFragmentMain(fragment, getArguments());
                        }
//                        NotifyService.notifyUpdateMenuLeft(getActivity(), getString(R.string.txt_home), 0);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void handleBackPressMain() {
        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayouts);
        View view = getView();
        if (view == null)
            return;
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    } else {
                        int canback = getFragmentManager().getBackStackEntryCount();
                        if (canback > 0 && canback <= 1) {
                            Fragment fragment = new FragmentHome();
                            onMoveFragmentMain(fragment, getArguments());

                        } else {
                            FragmentManager manager = getFragmentManager();
                            manager.popBackStack();
                            if (toolbar != null) {
                                toolbar.setTitle(R.string.txt_home);
                            }
                        }
//                        NotifyService.notifyUpdateMenuLeft(getActivity(), getString(R.string.txt_home), 0);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void updateBackActionbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFragmentBackstack();
                }
            });
        }
    }

    public void updateBackActionbarCustomBack() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new FragmentHome();
                    onMoveFragmentMain(fragment, getArguments());
                }
            });
        }
    }

    public void updateHomeActionbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayouts);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_menu);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    public void callSOS() {
        ImageView imgSOS = (ImageView) getActivity().findViewById(R.id.imgSOS);
        imgSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                onMoveFragmentMain(new SosFragment(), bundle);
            }
        });

    }

    public void removeFragmentBackstack() {
        if (getFragmentManager() != null) {
            boolean canback = getFragmentManager().getBackStackEntryCount() > 0;
            if (canback) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        }
    }

    public void setTitleActionBar(String title) {
        TextView tvToolbar = (TextView) getActivity().findViewById(R.id.tvTitleToolbar);
        tvToolbar.setText(title);

    }
    public void comeBackHomeScreen(){
        ImageView imgHome = (ImageView) getActivity().findViewById(R.id.imgHome);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                onMoveFragmentMain(new FragmentHome(), bundle);
            }
        });
    }

}