package com.secretbiology.managesmart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.secretbiology.managesmart.Home;
import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.adapters.AddCatAdapter;
import com.secretbiology.managesmart.database.Categories;
import com.secretbiology.managesmart.database.SubCategories;
import com.secretbiology.managesmart.database.models.CategoryModel;
import com.secretbiology.managesmart.database.models.SubCategoryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddCategory extends AppCompatActivity {

    //Constants
    public static String CATEGORY_CODE = "categoryCode";

    AddCatAdapter logadapter;
    ArrayList<String> data;
    ArrayList<Integer> subCatIDs;
    EditText mainCat;
    TextInputLayout mainCatLayout;
    int CategoryID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);

        //Get category code
        Intent intent = getIntent();
        CategoryID = intent.getIntExtra(CATEGORY_CODE,0);

        //Set recycler view
        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.addCatgory_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainCat = (EditText) findViewById(R.id.main_cat);
        Button addButton = (Button)findViewById(R.id.add_category_button);
        mainCatLayout = (TextInputLayout) findViewById(R.id.main_cat_layout);

        //Prepare data
        //Load all subcategories
        List<SubCategoryModel> subcats = new SubCategories(getBaseContext()).getAll();
        data = new ArrayList<>();
        subCatIDs = new ArrayList<>();

        if(new Categories(getBaseContext()).isAlreadyThere(CategoryID)) {
            addButton.setText("Change");
            CategoryModel category = new Categories(getBaseContext()).get(CategoryID);
            mainCat.setText(category.getName());
            //Sort all subcategories for current categories
            for (SubCategoryModel i : subcats) {
                if (i.getParentID() == CategoryID) {
                    data.add(i.getName());
                    subCatIDs.add(i.getId());
                }
            }
        }



        if(data.size()==0){
            data.add("Sub category 1");
            subCatIDs.add(0);
        }




        if(CategoryID!=1) {
            logadapter = new AddCatAdapter(data);
            recyclerView.setAdapter(logadapter);
            logadapter.setOnItemClickListener(new AddCatAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    ArrayList<String> temp = new ArrayList<String>();
                    if (data.size() != 1) {
                        data.remove(position);
                        if(subCatIDs.get(position)!=0){
                            new SubCategories(getBaseContext()).delete(subCatIDs.get(position));
                        }
                        subCatIDs.remove(position);
                        logadapter.notifyItemRemoved(position);
                        logadapter.notifyDataSetChanged();
                    } else {
                        data.remove(position);
                        data.add("Sub category 1");
                        subCatIDs.add(0);
                        logadapter.notifyDataSetChanged();
                    }
                }
            });
            logadapter.setAddSubCatListener(new AddCatAdapter.addSubCat() {
                @Override
                public void onItemClick(int position, View v) {
                    int numberOfTimes = 0;
                    for (String value : data) {
                        if (value.startsWith("Sub category ")) {
                            numberOfTimes++;
                        }
                    }
                    if (numberOfTimes == 0) {
                        data.add("Sub category 1");
                        subCatIDs.add(0);
                    } else {
                        String tempString = "Sub category " + String.valueOf(numberOfTimes + 1);
                        boolean foundItem = true;
                        while (foundItem) {
                            if (!data.contains(tempString)) {
                                data.add(tempString);
                                subCatIDs.add(0);
                                foundItem = false;
                            }
                            numberOfTimes++;
                            tempString = "Sub category " + String.valueOf(numberOfTimes + 1);
                        }
                    }
                    logadapter.notifyDataSetChanged();
                }
            });
            logadapter.onTextChange(new AddCatAdapter.textWatcher() {
                @Override
                public void onTextChanged(Editable editable, int position) {
                    data.set(position, editable.toString());
                }
            });

            logadapter.onUpClick(new AddCatAdapter.upClick() {
                @Override
                public void onItemClick(int position, View v) {
                    int targetPosition = 0;
                    if (position != 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = data.size() - 1;
                    }
                    Collections.swap(data, position, targetPosition);
                    Collections.swap(subCatIDs,position,targetPosition);
                    logadapter.notifyItemMoved(position, targetPosition);
                    logadapter.notifyDataSetChanged();

                }
            });

            logadapter.onDownClick(new AddCatAdapter.downClick() {
                @Override
                public void onItemClick(int position, View v) {

                    int targetPosition = 0;
                    if (position != data.size() - 1) {
                        targetPosition = position + 1;
                    } else {
                        targetPosition = 0;
                    }
                    Collections.swap(data, position, targetPosition);
                    Collections.swap(subCatIDs,position,targetPosition);
                    logadapter.notifyItemMoved(position, targetPosition);
                    logadapter.notifyDataSetChanged();
                }
            });
        }
        else {
            addButton.setEnabled(false);
            mainCat.setFocusable(false);
            Toast.makeText(getBaseContext(),"You can not change default category",Toast.LENGTH_LONG).show();
        }

        assert addButton != null;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainCat.getText().length()==0){
                    mainCatLayout.setError("Name can not be blank");
                }
                else
                {
                    if(CategoryID==0){
                        CategoryID = new Categories(getBaseContext()).add(mainCat.getText().toString());
                    }
                    else {
                        new Categories(getBaseContext()).update(new CategoryModel(CategoryID,mainCat.getText().toString()));
                    }
                }
                for (int i=0;i<data.size();i++){
                    if(data.get(i).length()!=0) { //Don't save edit box without any content
                        if (subCatIDs.get(i) == 0) {
                            new SubCategories(getBaseContext()).add(data.get(i), CategoryID);
                        } else {
                            new SubCategories(getBaseContext()).update(new SubCategoryModel(subCatIDs.get(i), CategoryID, data.get(i)));
                        }
                    }
                }
                startActivity(new Intent(AddCategory.this, Home.class));

            }
        });

    }

}
