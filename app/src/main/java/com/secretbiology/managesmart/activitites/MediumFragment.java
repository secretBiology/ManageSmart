package com.secretbiology.managesmart.activitites;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.AllFields;
import com.secretbiology.managesmart.database.ExpenseMedium;

import java.util.List;

/**
 * Created by Dexter on 12/31/2017.
 */

public class MediumFragment extends BottomSheetDialogFragment {

    private onMediumSelection selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.medium_fragment, container, false);

        if (getActivity() instanceof AddExpense) {

            AllFields allFields = ((AddExpense) getActivity()).getAllFields();
            setRecycler(rootView, allFields.getMediumList());
        }
        return rootView;
    }

    private void setRecycler(View rootView, final List<ExpenseMedium> mediumList) {
        RecyclerView recyclerView = rootView.findViewById(R.id.mf_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        MediumFragmentAdapter adapter = new MediumFragmentAdapter(mediumList, new MediumFragmentAdapter.OnClick() {
            @Override
            public void clicked(int position, int icon) {
                dismiss();
                selected.mediumSelected(mediumList.get(position), icon);
            }

        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            selected = (onMediumSelection) context;
        } catch (Exception e) {
            Toast.makeText(context, "attach fragment interface!", Toast.LENGTH_LONG).show();
        }
    }

    public interface onMediumSelection {
        void mediumSelected(ExpenseMedium medium, int icon);
    }
}