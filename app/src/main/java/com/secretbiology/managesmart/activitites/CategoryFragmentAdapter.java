package com.secretbiology.managesmart.activitites;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.helpers.general.General;
import com.secretbiology.managesmart.R;
import com.secretbiology.managesmart.common.IconRelations;
import com.secretbiology.managesmart.database.ExpenseCategory;

import java.util.List;

/**
 * Created by Dexter on 12/31/2017.
 */

public class CategoryFragmentAdapter extends RecyclerView.Adapter<CategoryFragmentAdapter.Holder> {

    private OnCategorySelect onCategorySelect;
    private List<ExpenseCategory> categoryList;

    public CategoryFragmentAdapter(List<ExpenseCategory> categoryList, OnCategorySelect onCategorySelect) {
        this.onCategorySelect = onCategorySelect;
        this.categoryList = categoryList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final ExpenseCategory category = categoryList.get(position);
        holder.text.setText(category.getName());
        final int ic = getIcon(category.getName());
        holder.icon.setImageResource(ic);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCategorySelect.categorySelected(category, ic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView text;
        ConstraintLayout layout;

        Holder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.cfi_cat_icon);
            text = itemView.findViewById(R.id.cfi_cat_text);
            layout = itemView.findViewById(R.id.cfi_cat_layout);
        }
    }

    private int getIcon(String name) {
        for (IconRelations r : IconRelations.values()) {
            if (r.toString().toLowerCase().equals(name.toLowerCase().trim())) {
                return r.getIcon();
            }
            for (String s : r.getAssociation().split(",")) {
                if (s.toLowerCase().trim().equals(name.toLowerCase().trim())) {
                    return r.getIcon();
                }
            }
            for (String s : r.getAssociation().split(",")) {
                if (General.percentSimilarity(s.trim().toLowerCase(), name.trim().toLowerCase()) > 70) {
                    return r.getIcon();
                }
            }
        }
        return R.drawable.icon_unknwon;
    }

    public interface OnCategorySelect {
        void categorySelected(ExpenseCategory category, int icon);
    }
}
