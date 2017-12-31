package com.secretbiology.managesmart.activitites;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.database.ExpenseCategory;

import java.util.List;

public class CategoryFragment extends BottomSheetDialogFragment {
    private onCategorySelected selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.category_fragment, container, false);

        if (getActivity() instanceof AddExpense) {

            AllFields allFields = ((AddExpense) getActivity()).getAllFields();
            setRecycler(rootView, allFields.getCategoryList());
        }
        return rootView;
    }

    private void setRecycler(View rootView, final List<ExpenseCategory> categoryList) {
        RecyclerView recyclerView = rootView.findViewById(R.id.cf_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 3));
        CategoryFragmentAdapter adapter = new CategoryFragmentAdapter(categoryList, new CategoryFragmentAdapter.OnCategorySelect() {
            @Override
            public void categorySelected(ExpenseCategory category, int icon) {
                selected.categorySelected(category, icon);
                dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            selected = (onCategorySelected) context;
        } catch (Exception e) {
            Toast.makeText(context, "attach fragment interface!", Toast.LENGTH_LONG).show();
        }
    }

    public interface onCategorySelected {
        void categorySelected(ExpenseCategory category, int icon);
    }
}
