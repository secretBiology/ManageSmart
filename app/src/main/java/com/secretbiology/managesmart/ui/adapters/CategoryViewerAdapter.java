package com.secretbiology.managesmart.ui.adapters;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.managesmart.R;

import java.util.ArrayList;

public class CategoryViewerAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem, tempChild;
    public ArrayList<Object> Childtem = new ArrayList<Object>();
    public LayoutInflater minflater;
    public Activity activity;
    private onEditButtonClick listener;

    public CategoryViewerAdapter(ArrayList<String> grList, ArrayList<Object> childItem) {
        groupItem = grList;
        this.Childtem = childItem;
    }

    public void setInflater(LayoutInflater mInflater, Activity act) {
        this.minflater = mInflater;
        activity = act;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Childtem.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.categoryviewer_headers, null);
        }
        text = (TextView) convertView.findViewById(R.id.category_header_text);
        text.setText(Html.fromHtml(tempChild.get(childPosition)));
        //convertView.setClickable(false);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) Childtem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupItem.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.category_viewer_parent, parent,false);
        }
        TextView txt =(TextView)convertView.findViewById(R.id.lblListHeader);
        ImageView editview = (ImageView) convertView.findViewById(R.id.image_edit);
        txt.setText(Html.fromHtml(groupItem.get(groupPosition)));
        if(isExpanded){
            editview.setVisibility(View.VISIBLE);
            convertView.setBackgroundResource(R.color.category_viewer_Selected);
        }else{
            editview.setVisibility(View.INVISIBLE);
            convertView.setBackgroundResource(R.color.category_viewer_background);
        }

        editview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditButtonClick(groupPosition, v.getId());
            }
        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setOneditButtonClick(onEditButtonClick listener)
    {
        this.listener = listener;
    }

    public interface onEditButtonClick
    {
        public void onEditButtonClick(int position, int id);
    }


}

