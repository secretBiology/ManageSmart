package com.secretbiology.managesmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.secretbiology.managesmart.database.DataBase;
import com.secretbiology.managesmart.database.queries.categoryMethods;

import java.util.Random;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        DataBase db = new DataBase(getBaseContext()); //Make new Database

        for (int i=0; i<10;i++){
            int k = new Random().nextInt(900000)+999999;
            Log.i("int",k+"");
        }

        new categoryMethods(getBaseContext()).addCategoy();
    }

}
