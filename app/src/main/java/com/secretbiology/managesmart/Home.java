package com.secretbiology.managesmart;

import android.os.Bundle;

import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.common.BaseActivity;
import com.secretbiology.managesmart.common.CreateDefaultValues;

public class Home extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setTitle(R.string.home);
        new CreateDefaultValues(getApplicationContext(), new CreateDefaultValues.OnFinish() {
            @Override
            public void addedDefaultValues(AllFields allFields) {

            }
        }).execute();
    }

    @Override
    protected int setNavigationMenu() {
        return R.id.nav_home;
    }
}
