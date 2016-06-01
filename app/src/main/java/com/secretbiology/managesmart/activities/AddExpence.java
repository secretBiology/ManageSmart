package com.secretbiology.managesmart.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.Utilities;
import com.secretbiology.managesmart.database.Categories;
import com.secretbiology.managesmart.database.SubCategories;
import com.secretbiology.managesmart.database.models.CategoryModel;
import com.secretbiology.managesmart.database.models.SubCategoryModel;
import com.secretbiology.managesmart.ui.adapters.CategoryViewerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AddExpence extends AppCompatActivity {

    EditText details, amount, venue;
    int category, subcategory;
    String date, time;
    Button dateBtn, timeBtn, catButton;

    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Integer> catIDs = new ArrayList<Integer>();
    ArrayList<Object> childItem = new ArrayList<Object>();
    int lastExpandedPosition = -1;
    int selectedItem = 0;
    String selectedCat;
    Intent currentIndent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expence);
        Log.i("CreatedCat", "gg");
        details = (EditText) findViewById(R.id.expense_details);
        amount = (EditText) findViewById(R.id.expense_amount);
        venue = (EditText) findViewById(R.id.expense_venue);
        category = 1; //1 is default : uncategorized
        subcategory = 1;
        dateBtn = (Button) findViewById(R.id.expense_date);
        timeBtn = (Button) findViewById(R.id.expense_time);
        catButton = (Button) findViewById(R.id.expense_category);
        date = new Utilities().date();
        time = new Utilities().time();

        dateBtn.setText(date);
        timeBtn.setText(time);
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CUrrent Cat", category + "");
            }
        });

        List<CategoryModel> list = new Categories(getBaseContext()).getAll();
        List<SubCategoryModel> sublist = new SubCategories(getBaseContext()).getAll();
        Collections.reverse(list);
        for (CategoryModel cat : list) {
            groupItem.add(cat.getName());
            catIDs.add(cat.getId());
            ArrayList<String> child = new ArrayList<>();
            if (sublist.size() != 0) {
                for (SubCategoryModel subcat : sublist) {
                    if (subcat.getParentID() == cat.getId()) {
                        child.add(subcat.getName());
                    }
                }
            }
            childItem.add(child);
        }

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddExpence.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.i("Time Set: ", selectedHour + ":" + selectedMinute);
                        timeBtn.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                //mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int monthOfYear = mcurrentTime.get(Calendar.MONTH);
                int dayOfMonth = mcurrentTime.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatepicker;
                mDatepicker = new DatePickerDialog(AddExpence.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.i("DATE", year + "" + dayOfMonth + "");
                    }
                }, year, monthOfYear, dayOfMonth);

                mDatepicker.show();

            }
        });

    }

    public void show() {
        final Dialog dialog = new Dialog(AddExpence.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.category_viewer);
        dialog.setCanceledOnTouchOutside(true);
        final ExpandableListView expListView = (ExpandableListView) dialog.findViewById(R.id.cat_list);
        final Button cancelDialog = (Button) dialog.findViewById(R.id.cancel_dialog);
        final TextView dialog_text = (TextView) dialog.findViewById(R.id.dialog_category);
        final TextView add_new = (TextView) dialog.findViewById(R.id.new_category_dialog);
        CategoryViewerAdapter mCatAdapter = new CategoryViewerAdapter(groupItem, childItem);
        mCatAdapter
                .setInflater(
                        (LayoutInflater) AddExpence.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                        AddExpence.this);

        // setting list adapter
        expListView.setAdapter(mCatAdapter);
        expListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.i("here", catIDs.get(groupPosition) + "  " + childPosition);
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);
                ArrayList<String> templist = (ArrayList<String>) childItem.get(groupPosition);
                catButton.setText(templist.get(childPosition));
                dialog.dismiss();
                return true;
            }
        });

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                selectedItem = 1;
                Log.i("Parent click", catIDs.get(groupPosition) + "  " + selectedItem);
                dialog_text.setText(groupItem.get(groupPosition));
                selectedCat = groupItem.get(groupPosition);
                catButton.setText(groupItem.get(groupPosition));
                cancelDialog.setText("Select");
                expListView.expandGroup(groupPosition);
                return true;
            }
        });


        mCatAdapter.setOneditButtonClick(new CategoryViewerAdapter.onEditButtonClick() {
            @Override
            public void onEditButtonClick(int position, int id) {
                currentIndent = new Intent(AddExpence.this, AddCategory.class);
                currentIndent.putExtra(AddCategory.CATEGORY_CODE, catIDs.get(position));
                startActivity(currentIndent);
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
                if (selectedItem == 1) {
                    //   expenseCat.setText(selectedCat);
                    Log.i("This item selected", "select");
                    selectedItem = 0;
                    dialog.dismiss();
                } else {
                    selectedItem = 0;
                    dialog.dismiss();
                }

            }
        });

        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(AddExpence.this, AddCategory.class);
                newIntent.putExtra(AddCategory.CATEGORY_CODE, 0);
                startActivity(newIntent);
            }
        });

    }


}
