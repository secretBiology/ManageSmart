package com.secretbiology.managesmart.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.activities.AddCategory;
import com.secretbiology.managesmart.database.Categories;
import com.secretbiology.managesmart.database.SubCategories;
import com.secretbiology.managesmart.database.models.CategoryModel;
import com.secretbiology.managesmart.database.models.SubCategoryModel;
import com.secretbiology.managesmart.ui.adapters.CategoryViewerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewer {
    Activity activity;
    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Integer> catIDs = new ArrayList<Integer>();
    ArrayList<Object> childItem = new ArrayList<Object>();
    int lastExpandedPosition = -1;
    int selectedItem = 0;
    String selectedCat;
    Intent currentIndent;

    public CategoryViewer(Activity activity) {
        this.activity = activity;
        List<CategoryModel> list = new Categories(activity.getBaseContext()).getAll();
        List<SubCategoryModel> sublist = new SubCategories(activity.getBaseContext()).getAll();

        for (CategoryModel cat : list){
            groupItem.add(cat.getName());
            catIDs.add(cat.getId());
            ArrayList<String> child = new ArrayList<>();
            if(sublist.size()!=0) {
                for (SubCategoryModel subcat : sublist) {
                    if (subcat.getParentID()==cat.getId()){
                        child.add(subcat.getName());
                    }
                }
            }
            childItem.add(child);
        }
    }

    public void show(){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.category_viewer);
        dialog.setCanceledOnTouchOutside(true);
        final ExpandableListView expListView = (ExpandableListView) dialog.findViewById(R.id.cat_list);
        final Button cancelDialog = (Button) dialog.findViewById(R.id.cancel_dialog);
        final TextView dialog_text = (TextView)dialog.findViewById(R.id.dialog_category);
        final TextView add_new = (TextView)dialog.findViewById(R.id.new_category_dialog);
        CategoryViewerAdapter mCatAdapter = new CategoryViewerAdapter(groupItem, childItem);
        mCatAdapter
                .setInflater(
                        (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                        activity);

        // setting list adapter
        expListView.setAdapter(mCatAdapter);
        expListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.i("here",groupPosition+"  "+childPosition);
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);
                ArrayList<String> templist = (ArrayList<String>) childItem.get(groupPosition);
                //expenseCat.setText(templist.get(childPosition));
                dialog.dismiss();
                return true;
            }
        });

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                selectedItem =1;
                Log.i("Parent click",groupPosition+"  "+selectedItem);
                dialog_text.setText(groupItem.get(groupPosition));
                //expenseCat.setText(groupItem.get(groupPosition));
                selectedCat = groupItem.get(groupPosition);
                cancelDialog.setText("Select");
                expListView.expandGroup(groupPosition);
                return true;
            }
        });

        mCatAdapter.setOneditButtonClick(new CategoryViewerAdapter.onEditButtonClick() {
            @Override
            public void onEditButtonClick(int position, int id) {
                currentIndent = new Intent(activity,AddCategory.class);
                currentIndent.putExtra(AddCategory.CATEGORY_CODE, catIDs.get(position));
                activity.startActivity(currentIndent);
                dialog.dismiss();
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        dialog.show();

        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedItem==1){
                 //   expenseCat.setText(selectedCat);
                    Log.i("This item selected","select");
                    selectedItem = 0;
                    dialog.dismiss();
                }
                else {
                    selectedItem = 0;
                    dialog.dismiss();
                }

            }
        });

        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(activity, AddCategory.class);
                newIntent.putExtra(AddCategory.CATEGORY_CODE,0);
                activity.startActivity(newIntent);
            }
        });

    }

}
