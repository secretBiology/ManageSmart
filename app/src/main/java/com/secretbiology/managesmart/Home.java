package com.secretbiology.managesmart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.secretbiology.managesmart.database.Categories;
import com.secretbiology.managesmart.database.Database;
import com.secretbiology.managesmart.database.SubCategories;
import com.secretbiology.managesmart.database.models.CategoryModel;
import com.secretbiology.managesmart.database.models.SubCategoryModel;

import java.util.List;

public class Home extends AppCompatActivity {

    static String TAG = Home.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
     }
}
