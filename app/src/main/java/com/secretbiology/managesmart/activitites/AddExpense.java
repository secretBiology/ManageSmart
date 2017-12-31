package com.secretbiology.managesmart.activitites;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.helpers.general.TimeUtils.DateConverter;
import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.common.BaseActivity;
import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.secretbiology.managesmart.activitites.KeyAdapter.EXTRA_KEY;
import static com.secretbiology.managesmart.activitites.KeyAdapter.INITIAL_AMOUNT;

public class AddExpense extends BaseActivity implements KeyAdapter.onKeyClick, CategoryFragment.onCategorySelected, MediumFragment.onMediumSelection {

    @BindView(R.id.ae_key_recycler)
    RecyclerView keyRecycler;
    @BindView(R.id.ae_amount)
    TextView amountText;
    @BindView(R.id.ae_date)
    TextView dateText;
    @BindView(R.id.ae_medium_text)
    TextView mediumText;
    @BindView(R.id.ae_cat_text)
    TextView catText;
    @BindView(R.id.ae_cat_icon)
    ImageView catIcon;
    @BindView(R.id.ae_medium_icon)
    ImageView mediumIcon;


    private AddExpenseViewModel viewModel;
    private AllFields allFields;
    private Calendar currentCalender = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        ButterKnife.bind(this);
        setTitle(R.string.add_expense);
        viewModel = ViewModelProviders.of(this).get(AddExpenseViewModel.class);
        if (allFields == null) {
            subscribe();
            viewModel.retrieveData(getApplicationContext());
        }

        keyRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        keyRecycler.setAdapter(new KeyAdapter(this));
        updateDate();
    }

    private void subscribe() {
        viewModel.getAllFields().observe(this, new Observer<AllFields>() {
            @Override
            public void onChanged(@Nullable AllFields af) {
                if (af != null) {
                    allFields = af;

                }
            }
        });
    }

    public AllFields getAllFields() {
        return allFields;
    }

    @Override
    protected int setNavigationMenu() {
        return 0;
    }

    @Override
    public void keyClicked(String s) {
        StringBuilder sb = new StringBuilder();
        if (!amountText.getText().toString().equals(INITIAL_AMOUNT)) {
            sb.append(amountText.getText());
        } else {
            if (s.equals(".")) {
                sb.append("0");
            }
        }
        if (!s.equals(EXTRA_KEY)) {
            if (s.equals(".")) {
                if (!sb.toString().contains(".")) {
                    sb.append(s);
                }
            } else {
                sb.append(s);
            }
            amountText.setText(sb);
        } else {
            amountText.setText(INITIAL_AMOUNT);
        }
    }

    @OnClick(R.id.ae_backspace)
    public void backSpace() {
        if (amountText.getText().toString().length() != 0 && !amountText.getText().toString().equals(INITIAL_AMOUNT)) {
            amountText.setText(amountText.getText().toString().substring(0, amountText.getText().length() - 1));
        }
        if (amountText.getText().length() == 0) {
            amountText.setText(INITIAL_AMOUNT);
        }
    }

    private void updateDate() {
        dateText.setText(DateConverter.convertToString(currentCalender, "EEEE, dd MMM yyyy"));
    }

    @OnClick(R.id.ae_date)
    public void pickDate() {
        new DatePickerDialog(AddExpense.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                currentCalender.set(Calendar.YEAR, i);
                currentCalender.set(Calendar.MONTH, i1);
                currentCalender.set(Calendar.DATE, i2);
                updateDate();
            }
        }, currentCalender.get(Calendar.YEAR), currentCalender.get(Calendar.MONTH), currentCalender.get(Calendar.DATE)).show();
    }

    @OnClick({R.id.ae_cat_text, R.id.ae_cat_icon, R.id.ae_cat_header})
    public void pickCategory() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new CategoryFragment();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }

    @OnClick({R.id.ae_medium_text, R.id.ae_medium_icon, R.id.ae_medium_header})
    public void pickMedium() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new MediumFragment();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }

    @Override
    public void categorySelected(ExpenseCategory category, int icon) {
        catText.setText(category.getName());
        catIcon.setImageResource(icon);
    }

    @Override
    public void mediumSelected(ExpenseMedium medium, int icon) {
        mediumText.setText(medium.getName());
        mediumIcon.setImageResource(icon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.ae_menu, menu);
        Drawable drawable = menu.findItem(R.id.action_done).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
        menu.findItem(R.id.action_done).setIcon(drawable);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                //TODO
                break;
        }
        return false;
    }
}
