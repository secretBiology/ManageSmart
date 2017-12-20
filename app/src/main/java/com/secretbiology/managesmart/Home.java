package com.secretbiology.managesmart;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.secretbiology.helpers.general.General;
import com.secretbiology.helpers.general.Log;
import com.secretbiology.managesmart.background.CreateDefaultValues;
import com.secretbiology.managesmart.background.IconRelations;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home extends BaseActivity {

    @BindView(R.id.editText)
    EditText editText;

    @BindView(R.id.textView)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ButterKnife.bind(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 1) {
                    ss();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Log.inform();

//        new CreateDefaultValues(getApplicationContext(), new CreateDefaultValues.OnFinish() {
//            @Override
//            public void addedDefaultValues(AllFields allFields) {
//
//            }
//        }).execute();
    }

    @Override
    protected int setNavigationMenu() {
        return R.id.nav_home;
    }

    @OnClick(R.id.button2)
    public void ss() {
        List<String> stList = new ArrayList<>();
        String ss = editText.getText().toString().trim().toLowerCase().replaceAll("[^a-zA-Z]+", " ");
        int identity = 0;
        for (IconRelations r : IconRelations.values()) {
            for (String s : r.getAssociation().split(",")) {
                String s2 = s.trim().toLowerCase();
                int sim = General.percentSimilarity(s2, ss);
                if (sim > identity) {
                    identity = sim;
                    stList.clear();
                    stList.add(r.toString());
                } else if (sim == identity && sim != 0) {
                    stList.add(r.toString());
                }
            }
        }

        StringBuilder t = new StringBuilder();
        for (String s : stList) {
            t.append(s);
            t.append(", ");
        }
        textView.setText(t.toString());


    }
}
