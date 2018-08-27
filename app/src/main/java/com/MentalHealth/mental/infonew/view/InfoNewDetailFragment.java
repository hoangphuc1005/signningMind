package com.MentalHealth.mental.infonew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.base.BaseFragment;

public class InfoNewDetailFragment extends BaseFragment {
    private TextView tvTitle;
    private TextView tvHeaderContent;
    private TextView tvLastContent;
    private ImageView imageContent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_info_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateBackActionbar();
        callSOS();
        initView();
        actionView();
        handleBackPressMain();
    }

    private void initView() {
        Bundle bundle = getArguments();
        int position = bundle.getInt("day");
        if (position>0) {
            setTitleActionBar("Ngày" +" "+ (position));
        } else {
            setTitleActionBar("Tin tức");
        }
        tvTitle = (TextView) findViewById(R.id.tvTitleInfoNewDetail);
        tvHeaderContent = (TextView) findViewById(R.id.tvTitleHeaderInfoNewDetail);
        tvLastContent = (TextView) findViewById(R.id.tvLastContentInfoNewDetail);
        imageContent = (ImageView) findViewById(R.id.imgContentInfoNewDetail);
    }

    private void actionView() {
        Bundle bundle = getArguments();
        int position = bundle.getInt("InfoNew");
        switch (position) {
            case 0:
                seTTextContent(getActivity().getString(R.string.dummy_title_info_1),
                        getActivity().getString(R.string.dummy_header_info_1),
                        getActivity().getString(R.string.dummy_last_content),
                        R.drawable.img_lo_au_info);
                break;
            case 1:
                seTTextContent(getContext().getString(R.string.dummy_title_info_2),
                        getActivity().getString(R.string.dummy_header_info_2),
                        getActivity().getString(R.string.last_content_2),
                        R.drawable.stress);
                break;
            case 2:
                seTTextContent(getActivity().getString(R.string.dummy_title_info_3),
                        getActivity().getString(R.string.dummy_header_info_3),
                        getActivity().getString(R.string.dummy_last_content_3),
                        R.drawable.ic_ttlc);
                break;
        }

    }

    private void seTTextContent(String title, String header, String lastContent, int imgContent) {
        tvTitle.setText(title);
        tvHeaderContent.setText(header);
        tvHeaderContent.setText(lastContent);
        imageContent.setImageResource(imgContent);
    }
}
