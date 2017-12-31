package com.secretbiology.managesmart.activities;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.secretbiology.helpers.general.General;
import com.secretbiology.helpers.general.Log;
import com.secretbiology.helpers.general.TimeUtils.DateConverter;
import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.database.ExpenseEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpenseFragment extends Fragment {

    @BindView(R.id.aef_date)
    TextView dateText;
    @BindView(R.id.aef_medium)
    TextView mediumtext;
    @BindView(R.id.aef_medium_icon)
    ImageView mediumIcon;
    @BindView(R.id.aef_keyboard)
    RecyclerView recyclerView;
    @BindView(R.id.aef_suggestions)
    RecyclerView suggestionRecycler;
    @BindView(R.id.aef_digit)
    TextView digit;
    @BindView(R.id.aef_currency)
    TextView currencyText;
    @BindView(R.id.aef_cat_details)
    TextView catDetails;

    private expenseMethods expenseMethods;
    private Calendar calendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_expense_fragment, container, false);
        ButterKnife.bind(this, view);
        setUpAdapter();
        setSuggestions();
        updateUI();
        return view;
    }

    private void setUpAdapter() {
        List<String> keyList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            keyList.add(String.valueOf(i));
        }
        keyList.add(".");
        keyList.add("0");
        keyList.add("enter");
        KeyboardAdapter adapter = new KeyboardAdapter(keyList, new KeyboardAdapter.onKeyPress() {
            @Override
            public void keyPressed(String key) {
                if (key.equals("enter")) {
                    //TODO
                } else {
                    StringBuilder builder = new StringBuilder();
                    if (!digit.getText().equals("0")) {
                        builder.append(digit.getText());
                    } else {
                        if (key.equals(".")) {
                            builder.append(digit.getText());
                        }
                    }
                    if (digit.getText().toString().contains(".") && key.equals(".")) {
                        General.makeShortToast(getContext(), "Invalid amount");
                    } else {
                        builder.append(key);
                        if (validAmount(builder.toString())) {
                            digit.setText(builder);
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private boolean validAmount(String s) {
        try {
            double d = Double.valueOf(s);
            if (d < 9999999999999999999.9) {
                return true;
            } else {
                General.makeLongToast(getContext(), "This amount is more than entire money in the world");
            }
        } catch (Exception ignored) {
            Log.inform(ignored);
        }
        return false;
    }

    private void setSuggestions() {
        List<SuggestionModel> modelList = new ArrayList<>();
        AllFields allFields = ((AddExpense) getActivity()).getAllFields();
        int maxValues = 0;
        if (allFields.getEntryList() != null) {
            for (ExpenseEntry entry : allFields.getEntryList()) {
                SuggestionModel model = new SuggestionModel();
                model.setAmount(entry.getAmount());
                model.setHeader(entry.getTitle());
                model.setFooter(entry.getCurrency());
                modelList.add(model);
                maxValues++;
                if (maxValues > 3) {
                    break;
                }
            }
        }

        if (modelList.size() < 3) {
            double[] initialSuggestions = new double[]{10.00, 100.00, 500.00};
            String[] initialText = new String[]{"Coffee", "Food", "T Shirt"};
            for (int j = 0; j < initialSuggestions.length; j++) {
                SuggestionModel model = new SuggestionModel();
                model.setAmount(initialSuggestions[j]);
                model.setHeader(initialText[j]);
                model.setFooter("INR");
                modelList.add(model);
            }

        }

        SuggestionAdapter adapter = new SuggestionAdapter(modelList, new SuggestionAdapter.setOnSuggestionClick() {
            @Override
            public void suggestionClicked(SuggestionModel model) {

            }
        });

        suggestionRecycler.setAdapter(adapter);
        suggestionRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void updateUI() {
        AllFields fields = ((AddExpense) getActivity()).getAllFields();
        updateDate();
        currencyText.setText("INR");
        String n = fields.getCurrentMedium().getName();
        mediumtext.setText(Html.fromHtml(getString(R.string.medium_select, n)));
        mediumIcon.setImageResource(MediumFragmentAdapter.getIcon(n));
        catDetails.setText(getString(R.string.category_details, fields.getCurrentCategory().getName()));
    }

    private void updateDate() {
        dateText.setText(DateConverter.convertToString(calendar, "EEEE, dd MMM yyyy"));
    }

    @OnClick(R.id.aef_backspace)
    public void backspace() {
        if (digit.getText().length() != 0) {
            digit.setText(digit.getText().toString().substring(0, digit.getText().length() - 1));
        }
        if (digit.getText().length() == 0) {
            digit.setText("0");
        }
    }


    @OnClick(R.id.aef_date)
    public void pickDate() {
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DATE, i2);
                updateDate();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            expenseMethods = (expenseMethods) context;
        } catch (Exception e) {
            Toast.makeText(context, "attach fragment interface!", Toast.LENGTH_LONG).show();
        }
    }


    @OnClick(R.id.aef_medium)
    public void pickMedium() {
        expenseMethods.openMediumList();
    }

    public void setMediumtext(String text, int icon) {
        mediumtext.setText(Html.fromHtml(getString(R.string.medium_select, text)));
        mediumIcon.setImageResource(icon);
    }

    interface expenseMethods {
        void openMediumList();
    }


}
