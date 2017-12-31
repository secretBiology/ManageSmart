package com.secretbiology.managesmart.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.database.ExpenseCategory;
import com.secretbiology.managesmart.database.ExpenseMedium;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpense extends AppCompatActivity implements CategoryFragment.CategorySelect, AddCategoryFragment.onCategoryCreation,
        AddExpenseFragment.expenseMethods, MediumFragment.onMediumSelection {

    private FragmentManager manager;
    private AllFields allFields;
    private ExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        if (allFields == null) {
            subscribe();
            viewModel.retrieveAllInformation(getApplicationContext());
            manager = getSupportFragmentManager();
        }

    }

    private void subscribe() {

        viewModel.getAllFields().observe(this, new Observer<AllFields>() {
            @Override
            public void onChanged(@Nullable AllFields af) {
                if (af != null) {
                    allFields = af;
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.add(R.id.ae_frame, new CategoryFragment());
                    ft.commit();
                }
            }
        });

    }

    public AllFields getAllFields() {
        return allFields;
    }

    private void addDetails() {
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        ft.replace(R.id.ae_frame, new AddExpenseFragment());
        ft.commit();
    }



    @Override
    public void onCategorySelect(ExpenseCategory category) {
        allFields.setCurrentCategory(category);
        addDetails();
    }

    @Override
    public void createNewCategory() {
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
        ft.replace(R.id.ae_frame, new AddCategoryFragment());
        ft.commit();
    }

    @Override
    public void addCategoryCancelled() {
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        ft.replace(R.id.ae_frame, new CategoryFragment());
        ft.commit();
    }

    @Override
    public void categoryAdded(ExpenseCategory cat) {
        allFields.setCurrentCategory(cat);
        addDetails();
    }


    @Override
    public void openMediumList() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new MediumFragment();
        bottomSheetDialogFragment.show(manager, bottomSheetDialogFragment.getTag());
    }

    @Override
    public void mediumSelected(ExpenseMedium medium, int icon) {
        allFields.setCurrentMedium(medium);
        ((AddExpenseFragment) manager.findFragmentById(R.id.ae_frame)).setMediumtext(medium.getName(), icon);
    }

    @Override
    public void onBackPressed() {
        if (manager.findFragmentById(R.id.ae_frame) instanceof AddExpenseFragment || manager.findFragmentById(R.id.ae_frame) instanceof AddCategoryFragment) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
            ft.replace(R.id.ae_frame, new CategoryFragment());
            ft.commit();
        } else {
            super.onBackPressed();
        }
    }
}
