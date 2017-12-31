package com.secretbiology.managesmart.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.database.ExpenseCategory;

/**
 * Created by Dexter on 12/23/2017.
 */

public class AddCategoryFragment extends Fragment {

    private onCategoryCreation categoryCreation;
    private ExpenseViewModel viewModel;
    private TextInputLayout textInputLayout;
    private FrameLayout progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_category_fragment, container, false);

        progress = view.findViewById(R.id.ac_progress);
        textInputLayout = view.findViewById(R.id.ac_edit_layout);

        viewModel = ViewModelProviders.of(getActivity()).get(ExpenseViewModel.class);

        progress.setVisibility(View.GONE);

        view.findViewById(R.id.ac_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryCreation.addCategoryCancelled();
            }
        });

        view.findViewById(R.id.ac_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textInputLayout.setErrorEnabled(false);
                if (textInputLayout.getEditText().getText().toString().trim().length() == 0) {
                    textInputLayout.setError("Name can not be empty");
                } else {
                    progress.setVisibility(View.VISIBLE);
                    viewModel.addCategory(getContext(), textInputLayout.getEditText().getText().toString().trim());
                }
            }
        });

        subscribe();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    private void subscribe() {
        viewModel.getCategory().observe(this, new Observer<ExpenseCategory>() {
            @Override
            public void onChanged(@Nullable ExpenseCategory category) {
                if (category != null) {
                    if (category.getId() == 0) {
                        progress.setVisibility(View.GONE);
                        textInputLayout.setError("This category already exists");
                    } else {
                        categoryCreation.categoryAdded(category);
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onCategoryCreation) {
            categoryCreation = (onCategoryCreation) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement CategorySelect");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        categoryCreation = null;
    }


    interface onCategoryCreation {
        void addCategoryCancelled();

        void categoryAdded(ExpenseCategory cat);
    }
}
