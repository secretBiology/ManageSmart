package com.secretbiology.managesmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.secretbiology.managesmart.activities.AddExpence;
import com.secretbiology.managesmart.database.Categories;
import com.secretbiology.managesmart.database.SubCategories;

public class Home extends AppCompatActivity {

    static String TAG = Home.class.getSimpleName();
    Categories categories;
    SubCategories subCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button btn = (Button) findViewById(R.id.debug_button);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddExpence.class));
            }
        });

    }
}
