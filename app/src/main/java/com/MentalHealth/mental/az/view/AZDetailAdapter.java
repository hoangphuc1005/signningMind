package com.MentalHealth.mental.az.view;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.az.model.AZChildModel;
import com.MentalHealth.mental.az.model.AZGroupModel;

import java.util.ArrayList;

import static android.view.View.MEASURED_SIZE_MASK;

/**
 * Created by hoangphuc on 15/07/2018.
 */
public class AZDetailAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<AZGroupModel> deptList;

    public AZDetailAdapter(Context context, ArrayList<AZGroupModel> deptList) {
        this.context = context;
        this.deptList = deptList;
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        ArrayList<AZChildModel> productList =
                deptList.get(i).getListChild();
        return productList.size();
    }

    @Override
    public Object getGroup(int i) {
        return deptList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        ArrayList<AZChildModel> productList =
                deptList.get(i).getListChild();
        return productList.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpand, View view, ViewGroup viewGroup) {
        AZGroupModel detailInfo = (AZGroupModel) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.custom_group_expand_a_z, null);
        }

        TextView sequence = (TextView) view.findViewById(R.id.tvTitleHeader);
        ImageView imgRaw = (ImageView) view.findViewById(R.id.imgGroupExpand);
        sequence.setText(detailInfo.getName());
        if (isExpand) {
            imgRaw.setImageResource(R.drawable.ic_arrow_down
            );
        } else {
            imgRaw.setImageResource(R.drawable.ic_arrow);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        AZChildModel detailInfo = (AZChildModel) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.custom_child_expand_a_z, null);
        }
        SpannableString spannableString = new SpannableString(Html.fromHtml(detailInfo.getName()));
        TextView sequence = (TextView) view.findViewById(R.id.tvChildAZ);
        sequence.setText(spannableString);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
