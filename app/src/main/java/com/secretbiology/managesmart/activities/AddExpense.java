package com.secretbiology.managesmart.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.Html;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.secretbiology.helpers.general.TimeUtils.DateConverter;
import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.common.BaseActivity;
import com.secretbiology.managesmart.common.MediumFragment;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpense extends BaseActivity implements MediumFragment.onMediumSelection {

    @BindView(R.id.ae_date)
    TextView date;
    @BindView(R.id.ae_medium_icon)
    ImageView mediumIcon;
    @BindView(R.id.ae_medium_text)
    TextView mediumText;
    @BindView(R.id.ae_amount_text)
    EditText amountText;
    @BindView(R.id.ae_currency)
    TextView currencyText;

    private Calendar entryTime = Calendar.getInstance();
    private AddExpenseViewModel viewModel;
    private AllFields allFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        setTitle(R.string.add_expense);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(AddExpenseViewModel.class);

        if (viewModel.getAllFields().getValue() == null) {
            toggleProgress();
            subscribe();
            viewModel.extractFields(getApplicationContext());
        }

    }

    @Override
    protected int setNavigationMenu() {
        return R.id.nav_add_expense;
    }

    private void subscribe() {
        viewModel.getAllFields().observe(this, new Observer<AllFields>() {
            @Override
            public void onChanged(@Nullable AllFields af) {
                if (af != null) {
                    toggleProgress();
                    allFields = af;
                    updateUI();
                }
            }
        });
    }

    private void updateUI() {
        updateDate();
        mediumText.setText(Html.fromHtml(getString(R.string.medium_select, "Cash")));
    }

    private void updateDate() {
        date.setText(DateConverter.convertToString(entryTime, "EEEE, dd MMM yyyy"));
    }

    @OnClick(R.id.ae_date)
    public void pickDate() {
        new DatePickerDialog(AddExpense.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                entryTime.set(Calendar.YEAR, i);
                entryTime.set(Calendar.MONTH, i1);
                entryTime.set(Calendar.DATE, i2);
                updateDate();
            }
        }, entryTime.get(Calendar.YEAR), entryTime.get(Calendar.MONTH), entryTime.get(Calendar.DATE)).show();
    }

    @OnClick(R.id.ae_previous_date)
    public void goPreviousDay() {
        entryTime.add(Calendar.DATE, -1);
        updateDate();
    }


    @OnClick(R.id.ae_next_date)
    public void goNextDay() {
        entryTime.add(Calendar.DATE, 1);
        updateDate();
    }

    public AllFields getAllFields() {
        return allFields;
    }

    @OnClick({R.id.ae_medium_text, R.id.ae_medium_icon})
    public void selectMedium() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new MediumFragment();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }


    @Override
    public void mediumSelected(ExpenseMedium medium, int icon) {
        mediumText.setText(Html.fromHtml(getString(R.string.medium_select, medium.getName())));
        mediumIcon.setImageResource(icon);
    }
}
