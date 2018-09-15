package com.MentalHealth.mental.library.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;

public class LibraryFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imgVideo;
    private ImageView imgDocument;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_library;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        actionView();
        setTitleActionBar("Thư viện");
        updateBackActionbarCustomBack();
        comeBackHomeScreen();
        handleBackPress();

    }

    private void initView() {
        imgVideo = (ImageView) findViewById(R.id.imgVideo);
        imgDocument = (ImageView) findViewById(R.id.imgDocument);

    }

    private void actionView() {
        imgVideo.setOnClickListener(this);
        imgDocument.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgVideo:
                onMoveParentFragments(new VideoDiaryFragment(), new Bundle());
                break;
            case R.id.imgDocument:
                onMoveParentFragments(new LibraryFragmentDetail(), new Bundle());
        }
    }
}
