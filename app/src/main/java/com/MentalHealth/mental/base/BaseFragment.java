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
import com.MentalHealth.mental.home.view.FragmentHome;
import com.MentalHealth.mental.home.view.SearchInfoFragment;
import com.MentalHealth.mental.sos.view.SosFragment;


public abstract class BaseFragment extends Fragment implements ViewTreeObserver.OnGlobalLayoutListener {


    private DrawerLayout mDrawerLayout;

    protected View mRootView;
    private Toolbar toolbar;
    private ImageView imgSOS;
    private ImageView imgSearch;
    private ImageView imgHome;
    private ImageView line;
    private TextView tvToolbar;

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
        initView();
        return mRootView;
    }

    private void initView() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        imgSOS = (ImageView) getActivity().findViewById(R.id.imgSOS);
        imgSearch = (ImageView) getActivity().findViewById(R.id.imgSearch);
        imgHome = (ImageView) getActivity().findViewById(R.id.imgHome);
        line = (ImageView) getActivity().findViewById(R.id.viewLine);
        tvToolbar = (TextView) getActivity().findViewById(R.id.tvTitleToolbar);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayouts);
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
        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_view, fragment);
            transaction.commit();
        }

    }

    public void onMoveFragmentSearch(Fragment fragment, final Bundle bundle) {
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

        View view = getView();
        if (view == null)
            return;
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                showToolBar();
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
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void handleBackPressMain() {
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
                    showToolBar();
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
        ImageView imgSos = (ImageView) getActivity().findViewById(R.id.imgSOS);
        imgSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                onMoveFragmentMain(new SosFragment(), bundle);
            }
        });

    }

    public void callSearch() {
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSearch.setVisibility(View.GONE);
                imgSOS.setVisibility(View.GONE);
                imgHome.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                tvToolbar.setVisibility(View.GONE);
                Bundle bundle = new Bundle();
                onMoveFragmentMain(new SearchInfoFragment(), bundle);
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

    public void comeBackHomeScreen() {

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                onMoveFragmentMain(new FragmentHome(), bundle);
            }
        });
    }

    public void showToolBar() {
        imgSearch.setVisibility(View.VISIBLE);
        imgSOS.setVisibility(View.VISIBLE);
        imgHome.setVisibility(View.VISIBLE);
        line.setVisibility(View.VISIBLE);
        tvToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolBar() {
        imgSearch.setVisibility(View.GONE);
        imgSOS.setVisibility(View.GONE);
        imgHome.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
        tvToolbar.setVisibility(View.GONE);
    }

}