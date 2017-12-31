package com.secretbiology.managesmart.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.database.ExpenseCategory;

import java.util.List;


public class CategoryFragment extends Fragment {

    private CategorySelect categorySelect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.select_category_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.aef_recycler);
        view.findViewById(R.id.aef_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });


        final List<ExpenseCategory> categories = ((AddExpense) getActivity()).getAllFields().getCategoryList();
        ExpenseCategory add = new ExpenseCategory();
        add.setDetails(getString(R.string.add_new));
        add.setName(getString(R.string.add_new));
        if (categories.size() > 0) {
            if (!categories.get(categories.size() - 1).getName().equals(getString(R.string.add_new))) {
                categories.add(add);
            }
        } else {
            categories.add(add);
        }

        CategoryAdapter adapter = new CategoryAdapter(categories, new CategoryAdapter.setOnCatClick() {
            @Override
            public void clicked(int position) {
                if (position == categories.size() - 1) {
                    categorySelect.createNewCategory();
                } else {
                    categorySelect.onCategorySelect(categories.get(position));
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CategorySelect) {
            categorySelect = (CategorySelect) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement CategorySelect");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        categorySelect = null;
    }

    interface CategorySelect {

        void onCategorySelect(ExpenseCategory category);

        void createNewCategory();
    }
}
