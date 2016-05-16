package com.secretbiology.managesmart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.secretbiology.managesmart.database.Categories;
import com.secretbiology.managesmart.database.SubCategories;
import com.secretbiology.managesmart.database.models.CategoryModel;
import com.secretbiology.managesmart.ui.Calculator;
import com.secretbiology.managesmart.ui.CategoryViewer;

public class Home extends AppCompatActivity {

    static String TAG = Home.class.getSimpleName();
    Categories categories;
    SubCategories subCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button btn = (Button)findViewById(R.id.debug_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Calculator(Home.this).makeCalculator();
            }
        });

    }

}
